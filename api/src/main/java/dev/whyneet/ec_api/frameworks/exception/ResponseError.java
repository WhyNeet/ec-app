package dev.whyneet.ec_api.frameworks.exception;

public record ResponseError(String code, String title, String detail, String instance) {
}
