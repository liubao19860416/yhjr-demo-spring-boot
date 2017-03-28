package com.yhjr.demo.vo;

public class ResultVO {
    public static final String SUCCESS = "0";
    public static final String FAILURE = "1";

    private String code;
    private String message;
    private Object data;

    public ResultVO() {
    	this.code = SUCCESS;
    }

    public ResultVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
