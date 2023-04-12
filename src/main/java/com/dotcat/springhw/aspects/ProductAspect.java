package com.dotcat.springhw.aspects;


import com.dotcat.springhw.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Aspect
@Component
public class ProductAspect {
    @Before("(execution(* com.dotcat.springhw.service.ProductService.create(..))" +
            "|| execution(* com.dotcat.springhw.service.ProductService.update(..))" +
            "|| execution(* com.dotcat.springhw.service.ProductService.delete(..))" +
            ") && args(product)")
    public void logOperations(JoinPoint joinPoint, Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid product price: " + product.getPrice() + " Should be more than 0");
        }
        String methodName = joinPoint.getSignature().getName();
        log.info("Preforming operation {} on product: {}", methodName, product);
    }

    @AfterReturning(pointcut = "execution(* com.dotcat.springhw.service.ProductService.getById(..))", returning = "product")
    public void logGettingProduct(Product product) {
        //  product.setPassword("<hidden>");
        log.info("Fetched product: {}", product);
    }

    @Around("execution(* com.dotcat.springhw.service.ProductService.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        log.info("Execution time for method: {} : {} ms", joinPoint.getSignature().getName(), (endTime - startTime));
        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.dotcat.springhw.service.ProductService.*(..))", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Exception ex) {
        log.info("Exception occurred in method: {}. Message: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }

    private final Map<Long, Product> productCache = new ConcurrentHashMap<>();

    @Around("execution(* com.dotcat.springhw.service.ProductService.getById(..)) && args(id)")
    public Object cacheGetById(ProceedingJoinPoint joinPoint, Long id) throws Throwable {
        if (productCache.containsKey(id)) {
            return productCache.get(id);
        }

        Object result = joinPoint.proceed();
        if (result instanceof Product) {
            productCache.put(id, (Product) result);
        }
        return result;
    }

    @After("execution(* com.dotcat.springhw.service.ProductService.create(..))")
    public void sendCreateNotification(JoinPoint joinPoint) {
        //Notification logic
        log.info("New product created: {}", joinPoint.getSignature().getName());
    }
}
