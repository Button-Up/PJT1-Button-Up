package com.ssafy.buttonup.exception;

/**
 * 출금 금액이 잔액보다 많을 때 발생하는 예외
 *
 * @author jiun kim
 * created on 2022-02-07
 */
public class BalanceOverException extends Exception {
    public BalanceOverException(String message) {
        super(message);
    }
}
