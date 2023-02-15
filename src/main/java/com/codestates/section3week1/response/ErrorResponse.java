package com.codestates.section3week1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private List<FieldError> fieldErrors;

    @Getter
    @AllArgsConstructor // 해당 객체 내에 있는 모든 변수들을 인수로 받는 생성자를 만들어냄
    public static class FieldError{ // ErrorResponse 클래스의 static 멤버 클래스

        private String field;
        private Object rejectedValue;
        private String reason;

//        public FieldError(String field, Object rejectedValue, String reason) { // << @AllArgsConstructor가 이걸 만들어 줌
//            this.field = field;
//            this.rejectedValue = rejectedValue;
//            this.reason = reason;
//        }
    }
}
