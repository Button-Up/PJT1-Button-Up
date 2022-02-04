package com.ssafy.buttonup.domain.service.account;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.AccountHistory;
import com.ssafy.buttonup.domain.model.entity.AccountHistoryType;
import com.ssafy.buttonup.domain.repository.account.AccountRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ChildRepository childRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ChildRepository childRepository) {
        this.accountRepository = accountRepository;
        this.childRepository = childRepository;
    }

    /**
     * 자녀 입출금 내역에서 가장 최근 잔액 조회
     *
     * @param child_seq 자녀
     * @return 잔액
     */
    @Override
    public int getBalanceByChild(long child_seq) {
        return accountRepository.findTopByChild_SeqOrderByDateDesc(child_seq).getBalance();
    }

    /**
     * 단추 입출금 내역 목록 조회
     *
     * @param child_seq 자녀
     * @return 입출금 내역 목록
     */
    @Override
    public List<HistoryResponse> getAccountHistoryList(long child_seq) {
        List<AccountHistory> historyList = accountRepository.findByChild_SeqOrderByDateDesc(child_seq);
        List<HistoryResponse> responseList = new ArrayList<>();

        for (AccountHistory history : historyList) {
            HistoryResponse response = HistoryResponse.builder()
                    .type(history.getType())
                    .category(history.getCategory())
                    .content(history.getContent())
                    .money(history.getMoney())
                    .balance(history.getBalance())
                    .date(history.getDate())
                    .build();
            responseList.add(response);
        }
        return responseList;
    }

    /**
     * 입출금 내역 생성
     *
     * @param historyRequest 추가할 내역
     * @param type 입/출금 구분 값
     * @return 추가 후 잔액
     */
    @Override
    @Transactional
    public int insertAccountHistory(HistoryRequest historyRequest, AccountHistoryType type) {
        AccountHistory.AccountHistoryBuilder builder = AccountHistory.builder();

        int balance = getBalanceByChild(historyRequest.getChild_seq());

        switch (type) {
            case 입금:
                builder.balance(balance += historyRequest.getMoney());
                break;
            case 출금:
                builder.balance(balance -= historyRequest.getMoney());
                break;
        }

        builder.category(historyRequest.getCategory())
                .type(type)
                .content(historyRequest.getContent())
                .money(historyRequest.getMoney())
                .child(childRepository.getById(historyRequest.getChild_seq()));

        accountRepository.save(builder.build());

        return balance;
    }
}
