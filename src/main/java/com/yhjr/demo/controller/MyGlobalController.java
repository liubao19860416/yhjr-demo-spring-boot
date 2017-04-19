package com.yhjr.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhjr.demo.exception.ErrorCodeConstant;
import com.yhjr.demo.utils.MessageSourceUtil;
import com.yhjr.demo.vo.ResultInfo;

/**
 * 异常跳转页面定义
 * 后续更新为JSON格式异常码数据
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年3月28日
 */
@Controller
@RequestMapping("/")
public class MyGlobalController /* implements ErrorController */{

	public static final String INDEX = "/index";
	public static final String ERROR_PATH = "/error/";
	public static final String ERROR_400 = ERROR_PATH+"400";
	public static final String ERROR_401 = ERROR_PATH+"401";
	public static final String ERROR_404 = ERROR_PATH+"404";
	public static final String ERROR_405 = ERROR_PATH+"405";
	public static final String ERROR_500 = ERROR_PATH+"500";
	public static final String NOAUTH = ERROR_PATH+"noauth";
	
	
	@Autowired
	private MessageSourceUtil messageSourceUtil;
	
	@RequestMapping("/")
	public String handleIndex() {
		return INDEX;
	}
	
	@RequestMapping(ERROR_PATH)
	public String handleError() {
		return ERROR_PATH+"error";
	}
	
	@ResponseBody
	@RequestMapping(value = ERROR_400,produces="application/json;charset=UTF-8")
	public Object handleError400(HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>(ErrorCodeConstant.ERROR_CODE_400,  
				messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_400));
	}
	
	@ResponseBody
	@RequestMapping(value = ERROR_401,produces="application/json;charset=UTF-8")
	public Object handleError401(HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>(ErrorCodeConstant.ERROR_CODE_401,  
				messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_401));
		//return "error/"+HttpStatus.UNAUTHORIZED.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = ERROR_404,produces="application/json;charset=UTF-8")
	public Object handleError404(HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>(ErrorCodeConstant.ERROR_CODE_404,  
				messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_404));
		//return "error/"+HttpStatus.NOT_FOUND.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = ERROR_405,produces="application/json;charset=UTF-8")
	public Object handleError405(HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>(ErrorCodeConstant.ERROR_CODE_405,  
				messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_405));
		//return "error/"+HttpStatus.METHOD_NOT_ALLOWED.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = ERROR_500,produces="application/json;charset=UTF-8")
	public Object handleError500(HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>(ErrorCodeConstant.ERROR_CODE_500,  
				messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_500));
		//return ERROR_PATH+HttpStatus.INTERNAL_SERVER_ERROR.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = NOAUTH)
	public Object handleNoAuth(HttpServletResponse response) {
	    response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
	    return new ResultInfo<String>(ErrorCodeConstant.ERROR_CODE_NOAUTH,  
	            messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_NOAUTH));
	    //return ERROR_PATH+HttpStatus.INTERNAL_SERVER_ERROR.toString();
	}

	/*@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}*/

}