package com.ssafy.buttonup.domain.service.account;

import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.Child;

import java.util.List;

/**
 * 입출금 내역 관련 서비스 interface
 *
 * @author jiun kim
 * created on 2022-02-02
 */
public interface AccountService {
    /**
     * 자녀 입출금 내역에서 가장 최근 잔액 조회
     *
     * @param child_seq 자녀
     * @return 잔액
     */
    public int getBalanceByChild(long child_seq);

    /**
     * 단추 입출금 내역 목록 조회
     *
     * @param child_seq 자녀
     * @return 입출금 내역 목록
     */
    public List<HistoryResponse> getAccountHistoryList(long child_seq);

    /**
     * 단추 입출금 내역 생성 - 출금, 입금
     */
    public void addAccountHistory();
}
