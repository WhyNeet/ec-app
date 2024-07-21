package dev.whyneet.ec_api.frameworks.exception.exceptions;

import dev.whyneet.ec_api.frameworks.exception.CommonException;
import org.springframework.http.HttpStatus;

public class UserException {
    public static class UserAlreadyExists extends CommonException {
        public UserAlreadyExists() {
            super("User already exists.", "User with this email already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    public static class UserDoesNotExist extends CommonException {
        public UserDoesNotExist() {
            super("User does not exist.", "User with these credentials does not exist.", HttpStatus.BAD_REQUEST);
        }
    }
}
