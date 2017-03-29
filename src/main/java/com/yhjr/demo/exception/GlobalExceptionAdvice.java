//package com.yhjr.demo.exception;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.ValidationException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import com.yhjr.demo.utils.MessageSourceUtil;
//import com.yhjr.demo.vo.ResultInfo;
//
//
///**
// * 全局异常处理
// * 
// * @Author  LiuBao
// * @Version 2.0
// *   2017年3月29日
// */
//@ControllerAdvice
//@ResponseBody
//public class GlobalExceptionAdvice {
//
//  private static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);
//  
//  @Autowired
//  private MessageSourceUtil messageSourceUtil;
//
//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(MissingServletRequestParameterException.class)
//  public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
//    logger.error("缺少请求参数", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.parameter.isnotpresent",messageSourceUtil.getMessage("required.parameter.isnotpresent"));
//  }
//
//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(HttpMessageNotReadableException.class)
//  public Object handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//    logger.error("参数解析失败", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.couldnot.readjson",messageSourceUtil.getMessage("required.couldnot.readjson"));
//  }
//
//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(BindException.class)
//  public Object handleBindException(BindException e) {
//    logger.error("参数绑定失败", e);
//    BindingResult result = e.getBindingResult();
//    FieldError error = result.getFieldError();
//    String field = error.getField();
//    String code = error.getDefaultMessage();
//    String message = String.format("%s:%s", field, code);
//    return new ResultInfo<String>()
//    		.buildFailure("required.couldnot.bindparam",message);
//  }
//
//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(ConstraintViolationException.class)
//  public Object handleServiceException(ConstraintViolationException e) {
//    logger.error("参数验证失败", e);
//    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//    ConstraintViolation<?> violation = violations.iterator().next();
//    String message = violation.getMessage();
//    return new ResultInfo<String>()
//    		.buildFailure("required.validation.exception",message);
//  }
//
//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(ValidationException.class)
//  public Object handleValidationException(ValidationException e) {
//    logger.error("参数验证失败", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.validation.exception",messageSourceUtil.getMessage("required.validation.exception"));
//  }
//
//  /**
//   * 405 - Method Not Allowed
//   */
//  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//  public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//    logger.error("不支持当前请求方法", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.method.notsupported",messageSourceUtil.getMessage("required.method.notsupported"));
//  }
//
//  /**
//   * 415 - Unsupported Media Type
//   */
//  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//  public Object handleHttpMediaTypeNotSupportedException(Exception e) {
//    logger.error("不支持当前媒体类型", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.contenttype.notsupported",messageSourceUtil.getMessage("required.contenttype.notsupported"));
//  }
//
//  /**
//   * 500 - Internal Server Error
//   */
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  @ExceptionHandler(Exception.class)
//  public Object handleException(Exception e) {
//    logger.error("通用异常", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.validation.exception", e.getMessage());
//  }
//
//  /**
//   * 操作数据库出现异常:名称重复，外键关联
//   */
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  @ExceptionHandler(DataIntegrityViolationException.class)
//  public Object handleException(DataIntegrityViolationException e) {
//    logger.error("操作数据库出现异常:", e);
//    return new ResultInfo<String>()
//    		.buildFailure(null, "操作数据库出现异常：字段重复、有外键关联等");
//  }
//  
//  /**
//   * 500 - Internal Server Error
//   */
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  @ExceptionHandler(BaseServiceException.class)
//  public Object handleServiceException(BaseServiceException e) {
//    logger.error("自定义业务逻辑异常", e);
//    return new ResultInfo<String>()
//    		.buildFailure("required.validation.exception",e.getMessage());
//  }
//  
//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public Object handleMethodArgumentNotValidException(HttpServletRequest request,  MethodArgumentNotValidException exception) {
//    logger.error("参数验证失败", exception);
//    BindingResult result = exception.getBindingResult();
//    FieldError error = result.getFieldError();
//    String field = error.getField();
//    String code = error.getDefaultMessage();
//    Object rejectedValue = error.getRejectedValue();
//    //解析此处返回非法的字段名称，原始值，错误信息  
//    String message = String.format("%s:%s", field, code);
//    return  new ResultInfo<Object>(ErrorCodeConstant.PARAMETER_ERROR, message, rejectedValue);  
//  }
//  
//}