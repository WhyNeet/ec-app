package dev.whyneet.ec_api.frameworks.exception.exceptions;

import dev.whyneet.ec_api.frameworks.exception.CommonException;
import org.springframework.http.HttpStatus;

public class UserException {
    public static class UserAlreadyExists extends CommonException {
        public UserAlreadyExists() {
            super("User already exists.", "User with this email already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
