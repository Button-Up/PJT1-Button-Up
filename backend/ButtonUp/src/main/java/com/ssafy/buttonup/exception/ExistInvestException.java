package com.ssafy.buttonup.exception;

/**
 * 이미 존재하는 종목을 추가할 때 발생하는 예외
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public class ExistInvestException extends Exception {
    public ExistInvestException(String message) {
        super(message);
    }
}
