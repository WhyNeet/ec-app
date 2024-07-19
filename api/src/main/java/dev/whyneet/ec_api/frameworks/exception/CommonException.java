package dev.whyneet.ec_api.frameworks.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends Exception {
    private final HttpStatus statusCode;
    private final String title;

    public CommonException(String title, String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.title = title;
    }
}
