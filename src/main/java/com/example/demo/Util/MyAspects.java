package com.example.demo.Util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspects {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
//Before
    
    @Before("execution(* com.example.demo.Controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
    	
        //Advice
        logger.info(" Request reached User Controller layer ");
        logger.info(" Allowed execution for {}", joinPoint.getSignature().getName());
        logger.info("Arguments :",joinPoint.getArgs());
    }
    @Before("execution(* com.example.demo.Service.*.*(..))")
    public void beforeService(JoinPoint joinPoint) {
    	
        //Advice
        logger.info(" Request reached User Service layer ");
        logger.info(" Allowed execution for {}", joinPoint.getSignature().getName());
        logger.info("Arguments :",joinPoint.getArgs());
    }
    @Before("execution(* com.example.demo.Data.*.*(..))")
    public void beforeData(JoinPoint joinPoint) {
    	
        //Advice
        logger.info(" Request reached User Data layer ");
        logger.info(" Allowed execution for {}", joinPoint.getSignature().getName());
        logger.info("Arguments :",joinPoint.getArgs());
    }
    
//AfterReturning
    
    @AfterReturning(value = "execution(* com.example.demo.Controller.*.*(..))",
            returning = "result")
    
        public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info(" After returning result at User Controller layer ");
            logger.info("{} returned with value {}", joinPoint, result);
        }
    @After(value = "execution(* com.example.demo.Controller.*.*(..))")
        public void after(JoinPoint joinPoint) {
        logger.info(" After  result at User Controller layer ");
            logger.info("after execution of {}", joinPoint);
        }

}
