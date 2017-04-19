package com.yhjr.demo.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhjr.demo.exception.ErrorCodeConstant;

/**
 * 统一返回数据实体类
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年3月29日
 */
public class ResultInfo<T> implements Serializable{
	private static final long serialVersionUID = 2858244548725271096L;
	private String code;
	private String message=StringUtils.EMPTY;
	private T data;

	public ResultInfo() {
		this.code = ErrorCodeConstant.SUCCESS;
	}

	public ResultInfo(T data) {
		this();
		this.data = data;
	}

	public ResultInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultInfo(String code, String message, T data) {
		this(code, message);
		this.data = data;
	}

	public ResultInfo<T> buildSuccess() {
		return this;
	}

	public ResultInfo<T> buildSuccess(T data) {
		this.setData(data);
		return this;
	}

	public ResultInfo<T> buildFailure() {
		this.setCode(ErrorCodeConstant.FAILURE);
		return this;
	}

	public ResultInfo<T> buildFailure(String code, String message) {
		if(StringUtils.isNotBlank(code)){
			this.setCode(code);
		}else{
			this.setCode(ErrorCodeConstant.FAILURE);
		}
		this.setMessage(message);
		return this;
	}

	@JSONField(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JSONField(name="message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JSONField(name="data")
	@JsonProperty(value = "data")
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
