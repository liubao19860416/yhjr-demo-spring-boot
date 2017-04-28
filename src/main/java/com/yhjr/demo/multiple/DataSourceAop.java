package com.yhjr.demo.multiple;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    private static final Logger logger = Logger.getLogger(DataSourceAop.class);
    
    @Pointcut("execution(* com.yhjr.demo..mapper..*.find*(..)) or execution(* com.yhjr.demo..mapper..*.get*(..))")
    public void readPointcut(){}
    
    @Pointcut("execution(* com.yhjr.demo..mapper..*.insert*(..)) or execution(* com.yhjr.demo..mapper..*.update*(..))")
    public void writePointcut(){}

    @Before("readPointcut()")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        logger.info("DataSource切换到：READ模式!");
    }

    @Before("writePointcut()")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        logger.info("DataSource切换到：WRITE模式!");
    }
    
    /*@Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void setWriteDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        Transactional datasource = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(Transactional.class);
        if(datasource!=null){
            if(datasource.readOnly()){
                DataSourceContextHolder.read();
                logger.info("dataSource切换到：read模式.");
            }else{
                DataSourceContextHolder.write();
                logger.info("dataSource切换到：write模式.");
            }
        }
        joinPoint.proceed();
    }*/
    
}