package dev.whyneet.ec_api.frameworks.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> defaultExceptionHandler(Exception exception, HttpServletRequest req) {
        ResponseError responseError = new ResponseError(String.format("UnhandledException/%s", exception.getClass().getName()), "An internal error occurred.", exception.getMessage(), req.getRequestURI());

        return ResponseEntity.internalServerError().body(responseError);
    }
}
