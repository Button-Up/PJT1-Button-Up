package com.ssafy.buttonup.domain.service.account;

import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.Child;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AccountService")
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    /**
     * 자녀 입출금 내역에서 가장 최근 잔액 조회
     *
     * @param child_seq 자녀
     * @return 잔액
     */
    @Override
    public int getBalanceByChild(long child_seq) {
        return 0;
    }

    /**
     * 단추 입출금 내역 목록 조회
     *
     * @param child_seq 자녀
     * @return 입출금 내역 목록
     */
    @Override
    public List<HistoryResponse> getAccountHistoryList(long child_seq) {
        return null;
    }

    /**
     * 단추 입출금 내역 생성 - 출금, 입금
     */
    @Override
    public void addAccountHistory() {

    }
}
