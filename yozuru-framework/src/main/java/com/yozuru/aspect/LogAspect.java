package com.yozuru.aspect;

import com.alibaba.fastjson.JSON;
import com.yozuru.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Yozuru
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    private static final String POINT_CUT = "@annotation(com.yozuru.annotation.SystemLog)";

    @Pointcut(POINT_CUT)
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //before
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("=============End=============" + System.lineSeparator());
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        //获取本次请求的request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String businessName = signature.getMethod().getAnnotation(SystemLog.class).businessName();
            log.info("=============Start=============");
            log.info("URL            : {}", requestAttributes.getRequest().getRequestURL().toString());
            log.info("BusinessName   : {}", businessName);
            log.info("HTTP Method    : {}", requestAttributes.getRequest().getMethod());
            log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            log.info("IP             : {}", requestAttributes.getRequest().getRemoteAddr());
            log.info("Request Args   : {}", joinPoint.getArgs());
        }
    }

    private void handleAfter(Object ret) {
        log.info("Response Args  : {}", JSON.toJSONString(ret));
    }

}
