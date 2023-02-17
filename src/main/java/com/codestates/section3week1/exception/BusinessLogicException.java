package com.codestates.section3week1.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException{ // Custom Exception 정의
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
