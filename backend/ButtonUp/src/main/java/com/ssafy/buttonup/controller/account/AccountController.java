package com.ssafy.buttonup.controller.account;

import com.ssafy.buttonup.domain.model.dto.account.request.HistoryRequest;
import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
import com.ssafy.buttonup.domain.service.account.AccountService;
import com.ssafy.buttonup.exception.BalanceOverException;
import io.swagger.annotations.*;
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
@Api(tags = "입출금 내역 관련 기능")
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
     * @param childSeq 자녀 키
     * @return 잔액
     */
    @GetMapping("balance/{child_seq}")
    @ApiOperation(value = "입출금 계좌 잔액 조회", notes = "자녀 키로 입출금 계좌 잔액을 조회합니다.")
    public ResponseEntity<Integer> checkBalance(@ApiParam(value = "자녀 키", required = true, example = "1") @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(accountService.getBalanceByChild(childSeq), HttpStatus.OK);
    }

    /**
     * 단추 입출금 목록 조회
     *
     * @param childSeq 자녀 키
     * @return 단추 입출금 목록
     */
    @GetMapping("histories/{child_seq}")
    @ApiOperation(value = "단추 입출금 목록 조회", notes = "자녀 키로 단추 입출금 목록을 조회합니다.")
    public ResponseEntity<List<HistoryResponse>> viewAccountHistoryList(@ApiParam(value = "자녀 키", required = true, example = "1") @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(accountService.getAccountHistoryList(childSeq), HttpStatus.OK);
    }

    /**
     * 입금 내역 추가
     *
     * @param request 입금 내역
     * @return 추가한 내역
     */
    @PostMapping("histories/deposit")
    @ApiOperation(value = "입금 내역 추가")
    public ResponseEntity<HistoryResponse> addAccountHistoryForDeposit(@ApiParam(value = "입금 내역 추가 요청 정보", required = true) @RequestBody HistoryRequest request) throws BalanceOverException {
        return new ResponseEntity<>(accountService.insertAccountHistory(request, AccountHistoryType.입금), HttpStatus.OK);
    }

    /**
     * 출금 내역 추가
     *
     * @param request 출금 내역
     * @return 추가한 내역
     */
    @PostMapping("histories/withdraw")
    @ApiOperation(value = "출금 내역 추가")
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    public ResponseEntity<HistoryResponse> addAccountHistoryForWithdraw(@ApiParam(value = "출금 내역 추가 요청 정보", required = true) @RequestBody HistoryRequest request) throws BalanceOverException {
        return new ResponseEntity<>(accountService.insertAccountHistory(request, AccountHistoryType.출금), HttpStatus.OK);
    }

    /**
     * 잔액 초과 예외 처리
     *
     * @param e
     * @return 잔액 초과
     */
    @ExceptionHandler(BalanceOverException.class)
    public ResponseEntity<String> balanceOverError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }
}
