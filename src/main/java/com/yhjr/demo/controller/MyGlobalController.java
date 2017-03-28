package com.yhjr.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhjr.demo.vo.ResultVO;

/**
 * 异常跳转页面定义
 * 后续更新为JSON格式异常码数据
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年3月28日
 */
@Controller("")
public class MyGlobalController implements ErrorController {

	public static final String INDEX = "/index";
	public static final String ERROR_PATH = "/error/";
	public static final String ERROR_400 = ERROR_PATH+"400";
	public static final String ERROR_401 = ERROR_PATH+"401";
	public static final String ERROR_404 = ERROR_PATH+"404";
	public static final String ERROR_405 = ERROR_PATH+"405";
	public static final String ERROR_500 = ERROR_PATH+"500";
	
	@RequestMapping("/")
	public String handleIndex() {
		return INDEX;
	}
	
	@RequestMapping(ERROR_PATH)
	public String handleError() {
		return ERROR_PATH+"error";
	}
	
	@ResponseBody
	@RequestMapping(value = ERROR_400)
	public Object handleError400(HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultVO("400",  "400全局异常!");
	}
	
	@RequestMapping(value = ERROR_401)
	public String handleError401() {
		return "error/"+HttpStatus.UNAUTHORIZED.toString();
	}
	
	@RequestMapping(value = ERROR_404)
	public String handleError404() {
		return "error/"+HttpStatus.NOT_FOUND.toString();
	}
	
	@RequestMapping(value = ERROR_405)
	public String handleError405() {
		return "error/"+HttpStatus.METHOD_NOT_ALLOWED.toString();
	}
	
	@RequestMapping(value = ERROR_500)
	public String handleError500() {
		return ERROR_PATH+HttpStatus.INTERNAL_SERVER_ERROR.toString();
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}