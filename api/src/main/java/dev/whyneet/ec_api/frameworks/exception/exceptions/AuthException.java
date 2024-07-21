package dev.whyneet.ec_api.frameworks.exception.exceptions;

import dev.whyneet.ec_api.frameworks.exception.CommonException;
import org.springframework.http.HttpStatus;

public class AuthException {
    public static class UserNotAuthenticated extends CommonException {

        public UserNotAuthenticated() {
            super("Not authenticated.", "Log in to access this resource.", HttpStatus.UNAUTHORIZED);
        }
    }

    public static class WrongUserCredentials extends CommonException {
        public WrongUserCredentials() {
            super("Wrong credentials.", "Invalid email or password.", HttpStatus.BAD_REQUEST);
        }
    }
}
