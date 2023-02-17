package com.codestates.section3week1.exception;

import lombok.Getter;

public enum ExceptionCode { // Custom 예외 정의하기 - 예외 멘트(상수) 정의
    MEMBER_NOT_FOUND(404, "Member Not Found");
    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
