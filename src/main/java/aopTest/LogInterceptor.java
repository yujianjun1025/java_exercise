package aopTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.*;

/**
 * Created by yjj on 15/10/17.
 */

@Aspect
@Component
public class LogInterceptor {
    public LogInterceptor() {
    }

    @Pointcut("execution(public * aopTest.User.*(..))")
    public void myMethod() {

        System.out.println("进入myMethod()");
    }

    @Before("execution(public * aopTest.User.*(..))")
    public void beforeMethod() {
        System.out.println("save start......");
    }

    @AfterReturning("execution(public * aopTest.User.*(..))")
    public void afterReturnning() {
        System.out.println("after save......");
    }

    @AfterThrowing("myMethod()")
    public void afterThrowing() {
        System.out.println("after throwing......");
    }

    @Around("myMethod()")
    public void aroundMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("method around start!");
        pjp.proceed();
        System.out.println("method around end!");
    }
}

