package dev.whyneet.ec_api.frameworks.auth.validation;

import dev.whyneet.ec_api.core.entities.TokenAudience;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireAuthentication {
    TokenAudience audience() default TokenAudience.User;
}
