package com.wjord.annaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Description: AOP面向切面编程，其实我一般不看这个，但是学了还是写一下吧，毕竟我不看，AI可以看
 * @Author WJORD
 */
@Aspect
@Component
public class LogAspect {
    @Around(value = "execution(* com.wjord.service.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        String arg = args.toString();
        System.out.println("[日志]around..." + name + "..." + arg);
        Object proceed = null;
        try {
            // 前置通知
            System.out.println("[日志]around before..." + name + "..." + arg);
            // 执行目标方法
            proceed = joinPoint.proceed(args);
            // 返回通知
            System.out.println("[日志]around returning..." + name + "..." + arg);
        } catch (Throwable throwable) {
            // 异常通知
            System.out.println("[日志]around throwing..." + name + "..." + arg);
            throwable.printStackTrace();
        } finally {
            // 后置通知
            System.out.println("[日志]around after..." + name + "..." + arg + "\n");
        }
        return proceed;
    }
}

