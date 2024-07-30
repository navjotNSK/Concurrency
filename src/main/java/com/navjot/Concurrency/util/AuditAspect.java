package com.navjot.Concurrency.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @Before("@annotation(audit)")  // Pointcut expression
    public void logMethodAccess(JoinPoint joinPoint, Audit audit) {  // Corrected to include JoinPoint and correctly annotated parameter
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        // Log the method name and the description provided in the annotation
        System.out.println("Executing " + methodName + " with audit description: " + audit.description());
    }

    // This advice runs after a method returns a value. It can also access the returned value.
//    @AfterReturning(pointcut = "@annotation(audit)", returning = "result")
//    public void afterReturningAdvice(JoinPoint joinPoint, Audit audit, Object result) {
//        System.out.println("Method returned value: " + result);
//        System.out.println("Audit description: " + audit.description());
//    }

    //This advice is executed if a method exits by throwing an exception. It can access the exception object.
//    @AfterThrowing(pointcut = "@annotation(audit)", throwing = "error")
//    public void afterThrowingAdvice(JoinPoint joinPoint, Audit audit, Throwable error) {
//        System.out.println("Method threw an exception: " + error);
//        System.out.println("Audit description: " + audit.description());
//    }

    //This advice runs after a method execution completes, regardless of the outcome (normal return or exception thrown). It does not have access to the return value or the exception.
//    @After("@annotation(audit)")
//    public void afterAdvice(JoinPoint joinPoint, Audit audit) {
//        System.out.println("Method execution completed.");
//        System.out.println("Audit description: " + audit.description());
//    }

    //This is the most powerful advice. It surrounds a method execution, meaning you can execute code before and after the method invocation. You can also choose to proceed or halt the execution of the method. It can modify the return value or handle the exception if needed.
//    @Around("@annotation(audit)")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Audit audit) throws Throwable {
//        System.out.println("Before method execution.");
//        Object result = null;
//        try {
//            result = joinPoint.proceed();  // Continue on the intercepted method
//        } catch (Throwable t) {
//            System.out.println("Exception in method: " + t);
//            throw t;  // Rethrow the exception
//        } finally {
//            System.out.println("After method execution.");
//        }
//        System.out.println("Audit description: " + audit.description());
//        return result;
//    }
}