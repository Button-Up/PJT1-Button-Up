package com.ssafy.buttonup.domain.service.account;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistory;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
import com.ssafy.buttonup.domain.repository.account.AccountRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.exception.BalanceOverException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * 입출금 내역과 관련된 서비스 클래스
 * 
 * @author jiun kim
 * created on 2022-02-02
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ChildRepository childRepository;
    private final EntityManager entityManager;


    /**
     * 자녀 입출금 내역에서 가장 최근 잔액 조회
     *
     * @param childSeq 자녀
     * @return 잔액
     */
    public int getBalanceByChild(long childSeq) {
        try {
            return accountRepository.findTopByChild_SeqOrderByDateDesc(childSeq).getBalance();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    /**
     * 단추 입출금 내역 목록 조회
     *
     * @param childSeq 자녀
     * @return 입출금 내역 목록
     */
    public List<HistoryResponse> getAccountHistoryList(long childSeq) {
        List<AccountHistory> historyList = accountRepository.findByChild_SeqOrderByDateDesc(childSeq);
        List<HistoryResponse> responseList = new ArrayList<>();

        for (AccountHistory history : historyList) {
            responseList.add(AccountHistory.toResponse(history));
        }
        return responseList;
    }

    /**
     * 입출금 내역 생성
     *
     * @param request 추가할 내역
     * @param type 입/출금 구분 값
     * @return 추가한 내역
     */
    @Transactional
    public HistoryResponse insertAccountHistory(HistoryRequest request, AccountHistoryType type) throws BalanceOverException {
        AccountHistory.AccountHistoryBuilder builder = AccountHistory.builder();

        int balance = getBalanceByChild(request.getChildSeq());

        switch (type) {
            case 입금:
                builder.balance(balance += request.getMoney());
                break;
            case 출금:
                if(balance < request.getMoney()) {
                    throw new BalanceOverException("잔액 초과");
                }
                builder.balance(balance -= request.getMoney());
                break;
        }

        AccountHistory accountHistory = builder.category(request.getCategory())
                .type(type)
                .category(request.getCategory())
                .content(request.getContent())
                .money(request.getMoney())
                .child(childRepository.getById(request.getChildSeq())).build();

        accountRepository.save(accountHistory);
        entityManager.refresh(entityManager.merge(accountHistory));


        return accountHistory.toResponse(accountHistory);
    }
}
