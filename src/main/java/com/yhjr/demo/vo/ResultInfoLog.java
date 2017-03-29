package com.yhjr.demo.vo;

/**
 * 日志记录封装类
 * 
 * @Author LiuBao
 * @Version 2.0 2017年3月29日
 */
public class ResultInfoLog<T> extends ResultInfo<T> {
	private static final long serialVersionUID = 3345619883940167384L;
	private String currentTimestamp;
	private String requestUrl;

	public ResultInfoLog() {
		super();
	}

	public ResultInfoLog(String code, String message, T data,String currentTimestamp, String requestUrl) {
		super(code, message, data);
		this.currentTimestamp = currentTimestamp;
		this.requestUrl = requestUrl;
	}
	public ResultInfoLog(String code, String message, T data, String requestUrl) {
		//TODO 时间修改
		this(code, message, data,requestUrl,null);
	}

	public String getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(String currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

}
