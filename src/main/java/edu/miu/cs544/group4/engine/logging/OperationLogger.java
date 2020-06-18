package edu.miu.cs544.group4.engine.logging;

/*
 * 
 * @author Dawed Nesru Muzeyen
 * @since 1.0
 * @created 13.06.2020
 * @copy right by me so far, lol
 * 
 * @description: This is the Advice class to run as AOP to log all the operations in the Service Layer
 * */



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class OperationLogger {

    private Logger log = LoggerFactory.getLogger(getClass());

    @AfterReturning(pointcut = "execution(* edu.miu.cs544.group4.engine.service..*.*(..))", returning = "result")
    public void logMethodAccessAfter(JoinPoint joinPoint, Object result)
    {
        if (result instanceof List) {
            List<?> resultList = (List<?>) result;
            log.info("END " + " method:" + joinPoint.getSignature().getName() + ": " + resultList.size());
        } else {
            log.info("END " + " method:" + joinPoint.getSignature().getName() + ": " + 1);
        }
    }

}
