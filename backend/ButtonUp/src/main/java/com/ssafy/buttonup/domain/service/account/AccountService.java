package com.ssafy.buttonup.domain.service.account;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
import com.ssafy.buttonup.exception.BalanceOverException;

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
     * @param childSeq 자녀
     * @return 잔액
     */
    int getBalanceByChild(long childSeq);

    /**
     * 단추 입출금 내역 목록 조회
     *
     * @param childSeq 자녀
     * @return 입출금 내역 목록
     */
    List<HistoryResponse> getAccountHistoryList(long childSeq);

    /**
     * 입출금 내역 생성
     *
     * @param request 추가할 내역
     * @param type 입/출금 구분 값
     * @return 추가 후 잔액
     */
    int insertAccountHistory(HistoryRequest request, AccountHistoryType type) throws BalanceOverException;
}
