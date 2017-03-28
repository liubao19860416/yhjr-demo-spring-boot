package com.yhjr.demo.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 日志切面类
 * 
 * @author LiuBao
 * @version 2.0
 * 2017年3月27日
 *
 */
@Aspect
@Component
public class ServiceLogMonitor {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceLogMonitor.class);
	
    @AfterReturning("execution(* com.yhjr.demo.service..*Service.*(..))")
    public void serviceLogRecording(JoinPoint joinPoint) {
    	log.info("ServiceLogMonitor.serviceLogRecording: {}" , joinPoint);
    	log.info("ServiceLogMonitor.serviceLogRecording:参数 {}" , JSON.toJSONString(joinPoint.getArgs()));
    }
}
