package com.yhjr.demo.exception;

/**
 * 基础自定义异常类
 * 
 * @Author LiuBao
 * @Version 2.0 2017年3月29日
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 6775179545328979398L;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}
	
}