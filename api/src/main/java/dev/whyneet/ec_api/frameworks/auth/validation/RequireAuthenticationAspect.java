package dev.whyneet.ec_api.frameworks.auth.validation;

import dev.whyneet.ec_api.frameworks.exception.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequireAuthenticationAspect {
    @Around("@annotation(RequireAuthentication)")
    public Object ensureUserAuthenticated(ProceedingJoinPoint joinPoint) throws Throwable {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) throw new AuthException.UserNotAuthenticated();
        return joinPoint.proceed();
    }
}
