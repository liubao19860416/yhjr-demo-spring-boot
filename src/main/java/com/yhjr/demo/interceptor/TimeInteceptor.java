package com.yhjr.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 请求时间拦截
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月1日
 */
public class TimeInteceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeInteceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long usedTime = endTime - startTime;
		// 修改ModelAndView属性信息
		if(modelAndView!=null){
			modelAndView.addObject("executeTime", usedTime);
		}
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOGGER.info("@@@@@@@@(URI):" + request.getRequestURI());
			//logger.info("@@@@@@@@Controller request method: [" + handler + "]");
			LOGGER.info("@@@@@@@@Controller层请求耗时("+ usedTime+ "ms)");
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception exception) throws Exception {
	}
	
}