package dev.whyneet.ec_api.frameworks.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCommonExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseError> defaultExceptionHandler(CommonException exception, HttpServletRequest req) {
        ResponseError responseError = new ResponseError(String.format("UnhandledException/%s", exception.getClass().getName()), exception.getTitle(), exception.getMessage(), req.getRequestURI());

        return ResponseEntity.status(exception.getStatusCode()).body(responseError);
    }
}
