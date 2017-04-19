package com.yhjr.demo.exception;

/**
 * 异常码常量定义类
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年3月29日
 */
public final class ErrorCodeConstant {
	
	public static final String SUCCESS = "0";
	public static final String FAILURE = "1";
	//默认异常提示文案信息
	public static final String ERROR_CODE_DEFAULT= "error.message.default";
	//NoAuth 访问资源未授权
	public static final String ERROR_CODE_NOAUTH = "error.noauth.exception";
	
	//请求参数信息不正确!
	public static final String REQUIRED_PARAMETER_ISNOTPRESENT= "required.parameter.isnotpresent";
	//请求JSON数据格式不正确!
	public static final String REQUIRED_COULDNOT_READJSON= "required.couldnot.readjson";
	//请求参数绑定失败!
	public static final String REQUIRED_COULDNOT_BINDPARAM= "required.couldnot.bindparam";
	//请求参数校验失败!
	public static final String REQUIRED_VALIDATION_EXCEPTION= "required.validation.exception";
	//请求方法{0}不支持!
	public static final String REQUIRED_METHOD_NOTSUPPORTED= "required.method.notsupported";
	//请求类型{0}不支持!
	public static final String REQUIRED_CONTENTTYPE_NOTSUPPORTED= "required.contenttype.notsupported";
	//操作数据库出现异常:字段重复/有外键关联等!
	public static final String REQUIRED_DATABASES_EXCEPTION= "required.databases.exception";
	
	//服务端异常响应码
	public static final String ERROR_CODE_400 = "error.400";
	public static final String ERROR_CODE_401 = "error.401";
	public static final String ERROR_CODE_404 = "error.404";
	public static final String ERROR_CODE_405 = "error.405";
	public static final String ERROR_CODE_500 = "error.500";
	
}
