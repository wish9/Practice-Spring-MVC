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
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;
    private ServiceError serviceErrors;
    private MethodNotAllowed methodErrors;
    private ExceptionError exceptionError;

    // ErrorResponse 생성자
    private ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, ServiceError serviceErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.serviceErrors = serviceErrors;
    }

//    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, List<MethodNotAllowed> methodErrors) {
//        this.fieldErrors = fieldErrors;
//        this.violationErrors = violationErrors;
//        this.methodErrors = methodErrors;
//    } // 생성자 매개변수 갯수 똑같이 오버로딩하면 안됨

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, ServiceError serviceErrors, MethodNotAllowed methodErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.serviceErrors = serviceErrors;
        this.methodErrors = methodErrors;
    }

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, ServiceError serviceErrors, MethodNotAllowed methodErrors, ExceptionError exceptionError) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.serviceErrors = serviceErrors;
        this.methodErrors = methodErrors;
        this.exceptionError = exceptionError;
    }

    // BindingResult에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult){
        return new ErrorResponse(FieldError.of1(bindingResult), null);
    }

    // Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    // 메서드 오버로딩
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of2(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode){
        // return new ErrorResponse(null, null, new ArrayList<>((Collection) new ServiceError(exceptionCode.getStatus(), exceptionCode.getMessage())));
        return new ErrorResponse(null, null, ServiceError.of3(exceptionCode));
    }

    public static ErrorResponse of(HttpRequestMethodNotSupportedException e){
        return new ErrorResponse(null, null, null, MethodNotAllowed.of4(e));
    }

    public static ErrorResponse of(Exception e){
        return new ErrorResponse(null, null, null, null, ExceptionError.of5(e));
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

    @Getter
    private static class ServiceError{
        private int status;
        private String message;

        public ServiceError(int status, String message) {
            this.status = status;
            this.message = message;

        }


        private static ServiceError of3(ExceptionCode exceptionCode){

            return new ServiceError(exceptionCode.getStatus(), exceptionCode.getMessage());

        }

    }

    @Getter
    private static class MethodNotAllowed{
        private int status;
        private String message;

        public MethodNotAllowed(int status, String message) {
            this.status = status;
            this.message = message;
        }

        private static MethodNotAllowed of4(HttpRequestMethodNotSupportedException e){
            return new MethodNotAllowed(ExceptionCode.METHOD_NOT_ALLOWED.getStatus(), ExceptionCode.METHOD_NOT_ALLOWED.getMessage());
        }
    }

    @Getter
    private static class ExceptionError{
        private int status;
        private String message;

        public ExceptionError(int status, String message) {
            this.status = status;
            this.message = message;
        }

        private static ExceptionError of5(Exception e){
            return new ExceptionError(ExceptionCode.INTERNAL_SERVER_ERROR.getStatus(), ExceptionCode.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}
