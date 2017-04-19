package com.yhjr.demo.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.yhjr.demo.wrapper.BodyReaderHttpServletRequestWrapper;
import com.yhjr.demo.wrapper.HttpHelper;

import sun.misc.BASE64Decoder;

/**
 * 基础信息校验接口
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月19日
 */
@SuppressWarnings("restriction")
public class BasicAuthorizeAttributeFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthorizeAttributeFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = null;
        if (request instanceof HttpServletRequest) {
            httpServletRequest = (HttpServletRequest) request;
        }
        String method = httpServletRequest.getMethod();
        if (RequestMethod.POST.name().equals(method)) {
            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
            String body = HttpHelper.getBodyString(requestWrapper);
            if (StringUtils.isNoneBlank(body)) {
                @SuppressWarnings("unchecked")
                Map<String, String> paramMap = JSON.parseObject(body, Map.class);
                String userToken = paramMap.get("userToken");
                LOGGER.info("获取得到的信息为:userToken={}",userToken);
                chain.doFilter(requestWrapper, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @SuppressWarnings("unused")
    private String getFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

}