package com.ssafy.buttonup.controller;

import com.ssafy.buttonup.exception.BalanceOverException;
import com.ssafy.buttonup.exception.ExistStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    /**
     * 잔액 초과 예외 처리
     *
     * @param e 예외
     * @return 잔액 초과
     */
    @ExceptionHandler(BalanceOverException.class)
    public ResponseEntity<String> balanceOverError(Exception e) {
//        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }

    /**
     * 이미 존재하는 투자 종목에 대한 예외 처리
     *
     * @param e 예외
     * @return 이미 존재하는 종목
     */
    @ExceptionHandler(ExistStockException.class)
    public ResponseEntity<String> ExistStockError(Exception e){
//        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
