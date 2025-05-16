package me.electronicsboy.noisehmbackend.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import me.electronicsboy.noisehmbackend.annotations.RateLimited;
import me.electronicsboy.noisehmbackend.exceptions.RateLimitExceededException;
import me.electronicsboy.noisehmbackend.services.RateLimiterService;

@Component
@Aspect
public class RateLimitAspect {

    private final RateLimiterService rateLimiterService;

    public RateLimitAspect(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Around("@annotation(me.electronicsboy.noisehmbackend.annotations.RateLimited)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimited rateLimited = method.getAnnotation(RateLimited.class);

        if (!rateLimiterService.isAllowed(username, rateLimited.limit())) {
            throw new RateLimitExceededException("Rate limit exceeded");
        }

        return joinPoint.proceed();
    }
}
