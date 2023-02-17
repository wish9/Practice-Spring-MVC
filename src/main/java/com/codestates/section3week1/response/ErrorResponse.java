package com.codestates.section3week1.response;

import com.codestates.section3week1.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse { // 에러문 간략화해서 필요한 정보만 담는 용도의 클래스
    private Integer status;
    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    public ErrorResponse(Integer status, String message, List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    // BindingResult에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult){
        return new ErrorResponse(null, null, FieldError.of1(bindingResult), null);
    }

    // Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    // 메서드 오버로딩
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, null, null, ConstraintViolationError.of2(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode){
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage(), null, null);
    }

    public static ErrorResponse of(HttpRequestMethodNotSupportedException e){
        return new ErrorResponse(ExceptionCode.METHOD_NOT_ALLOWED.getStatus(), ExceptionCode.METHOD_NOT_ALLOWED.getMessage(), null, null);
    }

    public static ErrorResponse of(Exception e){
        return new ErrorResponse(ExceptionCode.INTERNAL_SERVER_ERROR.getStatus(), ExceptionCode.INTERNAL_SERVER_ERROR.getMessage(), null, null);
    }



    @Getter
    public static class FieldError{ // ErrorResponse 클래스의 static 멤버 클래스

        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<FieldError> of1(BindingResult bindingResult) { // MethodArgumentNotValidException = BindingResult
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<ConstraintViolationError> of2(Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}
