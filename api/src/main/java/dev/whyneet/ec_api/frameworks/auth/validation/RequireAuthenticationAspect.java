package dev.whyneet.ec_api.frameworks.auth.validation;

import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.frameworks.exception.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class RequireAuthenticationAspect {
    @Around("@annotation(RequireAuthentication)")
    public Object ensureUserAuthenticated(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RequireAuthentication annotation = method.getAnnotation(RequireAuthentication.class);

        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
            throw new AuthException.UserNotAuthenticated();
        if (annotation.audience() == TokenAudience.User && !(SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal() instanceof User) || annotation.audience() == TokenAudience.Seller && !(SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal() instanceof Seller)) throw new AuthException.WrongAuthentication();
        return joinPoint.proceed();
    }
}
