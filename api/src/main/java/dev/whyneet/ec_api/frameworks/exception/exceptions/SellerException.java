package dev.whyneet.ec_api.frameworks.exception.exceptions;

import dev.whyneet.ec_api.frameworks.exception.CommonException;
import org.springframework.http.HttpStatus;

public class SellerException {
    public static class SellerAlreadyExists extends CommonException {
        public SellerAlreadyExists() {
            super("Seller already exists.", "Seller with this name already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    public static class SellerDoesNotExist extends CommonException {
        public SellerDoesNotExist() {
            super("Seller does not exist.", "Seller with these credentials does not exist.", HttpStatus.BAD_REQUEST);
        }
    }
}
