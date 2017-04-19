package com.yhjr.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yhjr.demo.exception.NoAuthException;

import redis.clients.jedis.JedisCluster;

/**
 * 登录状态及访问权限验证
 * 
 * @Author LiuBao
 * @Version 2.0 2017年4月1日
 */
public class LoginAuthInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginAuthInterceptor.class);
    
    @Autowired
    private JedisCluster   jedisCluster ;
    
    @Value("${noauth.redirect.path:/services}")
    private String   redirectPath ;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean resultFlag=true;
        String contextPath = request.getContextPath();
        logger.debug("结果contextPath:{}",contextPath);
        logger.debug("结果redirectPath:{}",redirectPath);
        
        //Object user = request.getSession().getAttribute("");
        String user = jedisCluster.get("user");
//        String user = jedisCluster.get("username");
        if(StringUtils.isBlank(user)){
            resultFlag= false;
            //方式1.1:转发或重定向
            //response.sendRedirect(redirectPath);
            //方式1.2:转发或重定向
            //redirectPath=redirectPath.replaceFirst(contextPath, "");
            //request.getRequestDispatcher(redirectPath).forward(request, response);
            //方式2:抛自定义异常
            throw new NoAuthException();
        }
        logger.debug("结果resultFlag:{}",resultFlag);
        return resultFlag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*if(ex instanceof NoAuthException){
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            ResultInfo<String> buildFailure = new ResultInfo<String>().buildFailure(ErrorCodeConstant.REQUIRED_NOAUTH_EXCEPTION, ex.getMessage());
            response.getWriter().write(JSON.toJSONString(buildFailure));
        }*/
        logger.debug("无异常信息,执行结束...");
    }
    
}