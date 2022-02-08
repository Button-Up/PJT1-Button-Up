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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestService {
    public final RequestHistoryRepository requestHistoryRepository;
    public final AccountService accountService;
    public final ChildRepository childRepository;

    @Transactional(rollbackFor = Exception.class)
    public void insertExchange(ExchangeRequest request) throws BalanceOverException {
        long childSeq = request.getChildSeq();
        Child child = childRepository.getById(childSeq);
        int price = request.getPrice();

        RequestHistoryType type = RequestHistoryType.EXCHANGE;
        RequestHistoryStatus status = RequestHistoryStatus.INCOMPLETE;
        RequestHistory requestHistory = RequestHistory.builder()
                .type(type)
                .status(status)
                .price(price)
                .child(child)
                .build();
        requestHistoryRepository.save(requestHistory);

        AccountHistoryCategory category = AccountHistoryCategory.환전;
        AccountHistoryType accountHistoryType = AccountHistoryType.출금;
        final String content = "환전 요청";
        HistoryRequest historyRequest = HistoryRequest.builder()
                .category(category)
                .content(content)
                .money(price)
                .childSeq(childSeq)
                .build();
        accountService.insertAccountHistory(historyRequest, accountHistoryType);
    }

    @Transactional
    public void changeStatus(long responseHistorySeq, RequestHistoryStatus status) throws BalanceOverException {
        RequestHistory requestHistory = requestHistoryRepository.getById(responseHistorySeq);
        requestHistory.changeStatus(status);
        requestHistoryRepository.save(requestHistory);

        if(status.equals(RequestHistoryStatus.REJECT)) {
            AccountHistoryCategory category = AccountHistoryCategory.환전;
            AccountHistoryType accountHistoryType = AccountHistoryType.입금;
            final String content = "환전 거절";
            HistoryRequest historyRequest = HistoryRequest.builder()
                    .category(category)
                    .content(content)
                    .money(requestHistory.getPrice())
                    .childSeq(requestHistory.getChild().getSeq())
                    .build();
            accountService.insertAccountHistory(historyRequest, accountHistoryType);
        }
    }

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
