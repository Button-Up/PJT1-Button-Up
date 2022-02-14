package com.ssafy.buttonup.exception;

/**
 * 직업이 없을 때 예외
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
public class NullJobException extends Exception {
    /**
     * 직업 없을 때 예외
     *
     * @param message the detail message.
     */
    public NullJobException(String message) {
        super(message);
    }
}
