package com.ssafy.buttonup.controller;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.AccountHistoryType;
import com.ssafy.buttonup.domain.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 단추 관련 컨트롤러
 *
 * @author jiun kim
 * created on 2022-02-02
 */
@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 입출금 계좌 잔액을 확인
     *
     * @param child_seq 자녀 코드
     * @return 잔액
     */
    @GetMapping("balance/{child_seq}")
    public ResponseEntity<Integer> checkBalance(@PathVariable long child_seq) {
        return new ResponseEntity<>(accountService.getBalanceByChild(child_seq), HttpStatus.OK);
    }

    /**
     * 단추 입출금 목록 조회
     *
     * @param child_seq 자녀 코드
     * @return 단추 입출금 목록
     */
    @GetMapping("history/list/{child_seq}")
    public ResponseEntity<List<HistoryResponse>> viewAccountHistoryList(@PathVariable long child_seq) {
        return new ResponseEntity<>(accountService.getAccountHistoryList(child_seq), HttpStatus.OK);
    }

    /**
     * 입금 내역 추가
     *
     * @param historyRequest 입금 내역
     * @return 잔액
     */
    @PostMapping("history/deposit")
    public ResponseEntity<Integer> addAccountHistoryForDeposit(@RequestBody HistoryRequest historyRequest) {
        return new ResponseEntity<>(accountService.insertAccountHistory(historyRequest, AccountHistoryType.입금), HttpStatus.OK);
    }

    /**
     * 출금 내역 추가
     *
     * @param historyRequest 출금 내역
     * @return 잔액
     */
    @PostMapping("history/withdraw")
    public ResponseEntity<Integer> addAccountHistoryForWithdraw(@RequestBody HistoryRequest historyRequest) {
        return new ResponseEntity<>(accountService.insertAccountHistory(historyRequest, AccountHistoryType.출금), HttpStatus.OK);
    }
}
