package com.ssafy.buttonup.controller;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
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
@RequestMapping("accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 입출금 계좌 잔액을 확인
     *
     * @param childSeq 자녀 코드
     * @return 잔액
     */
    @GetMapping("balance/{child_seq}")
    public ResponseEntity<Integer> checkBalance(@PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(accountService.getBalanceByChild(childSeq), HttpStatus.OK);
    }

    /**
     * 단추 입출금 목록 조회
     *
     * @param childSeq 자녀 코드
     * @return 단추 입출금 목록
     */
    @GetMapping("histories/{child_seq}")
    public ResponseEntity<List<HistoryResponse>> viewAccountHistoryList(@PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(accountService.getAccountHistoryList(childSeq), HttpStatus.OK);
    }

    /**
     * 입금 내역 추가
     *
     * @param request 입금 내역
     * @return 잔액
     */
    @PostMapping("histories/deposit")
    public ResponseEntity<Integer> addAccountHistoryForDeposit(@RequestBody HistoryRequest request) {
        return new ResponseEntity<>(accountService.insertAccountHistory(request, AccountHistoryType.입금), HttpStatus.OK);
    }

    /**
     * 출금 내역 추가
     *
     * @param request 출금 내역
     * @return 잔액
     */
    @PostMapping("histories/withdraw")
    public ResponseEntity<Integer> addAccountHistoryForWithdraw(@RequestBody HistoryRequest request) {
        return new ResponseEntity<>(accountService.insertAccountHistory(request, AccountHistoryType.출금), HttpStatus.OK);
    }
}
