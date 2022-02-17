package com.ssafy.buttonup.domain.service.request;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.request.request.ExchangeRequest;
import com.ssafy.buttonup.domain.model.dto.request.response.RequestHistoryResponse;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryCategory;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistory;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryStatus;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryType;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.repository.request.RequestHistoryRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.service.account.AccountService;
import com.ssafy.buttonup.exception.BalanceOverException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 요청 내역 서비스
 * @author jeongyeon woo
 * created on 2022-02-07
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestService {
    public final RequestHistoryRepository requestHistoryRepository;
    public final AccountService accountService;
    public final ChildRepository childRepository;

    /**
     * 환전 요청 내역 추가 및 입출금 내역 추가(입금)
     * @param request
     * @throws BalanceOverException
     */
    @Transactional(rollbackFor = Exception.class)   // 해당 Exception이 발생해도 rollback 진행!(단추를 초과해 환전 요청 한 경우)
    public void insertExchange(ExchangeRequest request) throws BalanceOverException {
        long childSeq = request.getChildSeq();
        Child child = childRepository.getById(childSeq);
        int price = request.getPrice();

        RequestHistoryType type = RequestHistoryType.환전;
        RequestHistoryStatus status = RequestHistoryStatus.미완료;
        RequestHistory requestHistory = RequestHistory.builder()
                .type(type)
                .status(status)
                .price(price)
                .child(child)
                .build();
        requestHistoryRepository.save(requestHistory);      // 요청 내역 추가

        AccountHistoryCategory category = AccountHistoryCategory.환전;
        AccountHistoryType accountHistoryType = AccountHistoryType.출금;
        final String content = "환전 요청";
        HistoryRequest historyRequest = HistoryRequest.builder()
                .category(category)
                .content(content)
                .money(price)
                .childSeq(childSeq)
                .build();
        accountService.insertAccountHistory(historyRequest, accountHistoryType);       // 입출금 내역 추가(출금)
    }

    /**
     * 요청 내역 상태 변환
     * REJECT, APPROVE
     * @param responseHistorySeq
     * @param status
     * @throws BalanceOverException
     */
    @Transactional(rollbackFor = Exception.class)
    public void changeStatus(long responseHistorySeq, RequestHistoryStatus status) throws BalanceOverException {
        RequestHistory requestHistory = requestHistoryRepository.getById(responseHistorySeq);
        requestHistory.changeStatus(status);
        requestHistoryRepository.save(requestHistory);

        if(status.equals(RequestHistoryStatus.거절)) {        // 거절인 경우, 입출금 내역에 입금이 추가되어야 함
            AccountHistoryCategory category = AccountHistoryCategory.환전;
            AccountHistoryType accountHistoryType = AccountHistoryType.입금;
            final String content = "환전 거절";
            HistoryRequest historyRequest = HistoryRequest.builder()
                    .category(category)
                    .content(content)
                    .money(requestHistory.getPrice())
                    .childSeq(requestHistory.getChild().getSeq())
                    .build();
            accountService.insertAccountHistory(historyRequest, accountHistoryType);    // 입출금 내역 추가
        }
    }

    /**
     * 아이의 요청 내역 목록 조회
     * @param childSeq
     * @return
     */
    public List<RequestHistoryResponse> findByChildSeq(long childSeq) {
        List<RequestHistory> requestHistories = requestHistoryRepository.findByChild_SeqOrderByDateAsc(childSeq);
        List<RequestHistoryResponse> responses = new ArrayList<>();
        for(RequestHistory requestHistory : requestHistories) {
            RequestHistoryResponse response = RequestHistoryResponse.builder()
                    .seq(requestHistory.getSeq())
                    .type(requestHistory.getType())
                    .date(requestHistory.getDate())
                    .status(requestHistory.getStatus())
                    .price(requestHistory.getPrice())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    /**
     * 요청 내역 단일 조회
     * @param requestSeq
     * @return
     */
    public RequestHistoryResponse findBySeq(long requestSeq) {
        RequestHistory requestHistory = requestHistoryRepository.getById(requestSeq);
        RequestHistoryResponse response = RequestHistoryResponse.builder()
                .seq(requestHistory.getSeq())
                .type(requestHistory.getType())
                .date(requestHistory.getDate())
                .status(requestHistory.getStatus())
                .price(requestHistory.getPrice())
                .build();
        return response;
    }
}
