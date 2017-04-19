package com.yhjr.demo.exception;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yhjr.demo.utils.MessageSourceUtil;
import com.yhjr.demo.vo.ResultInfo;

/**
 * 全局异常处理器
 * 
 * @Author LiuBao
 * @Version 2.0 2017年3月29日
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionAdvice {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

	@Autowired
	private MessageSourceUtil messageSourceUtil;

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleMissingServletRequestParameterException(HttpServletResponse response,MissingServletRequestParameterException exception) {
		logger.error("缺少请求参数", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_PARAMETER_ISNOTPRESENT,
				messageSourceUtil.getMessage(ErrorCodeConstant.REQUIRED_PARAMETER_ISNOTPRESENT));
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleHttpMessageNotReadableException(HttpServletResponse response,HttpMessageNotReadableException exception) {
		logger.error("参数解析失败", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_COULDNOT_READJSON,
				messageSourceUtil.getMessage(ErrorCodeConstant.REQUIRED_COULDNOT_READJSON));
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Object handleBindException(HttpServletResponse response,BindException exception) {
		logger.error("参数绑定失败", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		BindingResult result = exception.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_COULDNOT_BINDPARAM, message);
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Object handleServiceException(HttpServletResponse response,ConstraintViolationException exception) {
		logger.error("参数Constraint验证失败", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_VALIDATION_EXCEPTION, message);
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public Object handleValidationException(HttpServletResponse response,ValidationException exception) {
		logger.error("参数验证失败", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_VALIDATION_EXCEPTION,
				messageSourceUtil.getMessage(ErrorCodeConstant.REQUIRED_VALIDATION_EXCEPTION));
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Object handleHttpRequestMethodNotSupportedException(HttpServletRequest request,HttpServletResponse response,HttpRequestMethodNotSupportedException exception) {
	    String method = request.getMethod();
		logger.error("不支持当前请求{}方法:{}", exception,method);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_METHOD_NOTSUPPORTED,
				messageSourceUtil.getMessage(ErrorCodeConstant.REQUIRED_METHOD_NOTSUPPORTED,new Object[]{method}));
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Object handleHttpMediaTypeNotSupportedException(HttpServletRequest request,HttpServletResponse response,HttpMediaTypeNotSupportedException exception) {
		String contentType = request.getContentType();
		logger.error("不支持当前媒体类型[{}],error:{}", exception,contentType);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_CONTENTTYPE_NOTSUPPORTED,
				messageSourceUtil.getMessage(ErrorCodeConstant.REQUIRED_CONTENTTYPE_NOTSUPPORTED,new Object[]{contentType}));
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Object handleException(HttpServletResponse response,Exception exception) {
		logger.error("通用异常", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.ERROR_CODE_DEFAULT,
				messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_DEFAULT));
	}

	/**
	 * 操作数据库出现异常:名称重复，外键关联
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public Object handleDataIntegrityViolationException(HttpServletResponse response,DataIntegrityViolationException exception) {
		logger.error("操作数据库出现异常:", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_DATABASES_EXCEPTION,
				messageSourceUtil.getMessage(ErrorCodeConstant.REQUIRED_DATABASES_EXCEPTION));
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BaseServiceException.class)
	public Object handleServiceException(HttpServletResponse response,BaseServiceException exception) {
		logger.error("自定义业务逻辑异常", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		return new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_VALIDATION_EXCEPTION, exception.getMessage());
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleMethodArgumentNotValidException(HttpServletResponse response,MethodArgumentNotValidException exception) {
		logger.error("参数校验失败", exception);
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		BindingResult result = exception.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		Object rejectedValue = error.getRejectedValue();
		// 解析此处返回非法的字段名称，原始值，错误信息
		String message = String.format("%s:%s", field, code);
		return new ResultInfo<Object>(ErrorCodeConstant.ERROR_CODE_DEFAULT, message, rejectedValue);
	}
	
	/**
	 *NoAuthException 访问资源未授权
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoAuthException.class)
	public Object handleNoAuthException(HttpServletResponse response,NoAuthException exception) {
	    logger.error("访问资源未授权", exception);
	    response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
	    //return new ResultInfo<String>().buildFailure(ErrorCodeConstant.ERROR_CODE_NOAUTH, exception.getMessage());
	    return new ResultInfo<String>().buildFailure(ErrorCodeConstant.ERROR_CODE_NOAUTH,  
	            messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_NOAUTH));
	}

}