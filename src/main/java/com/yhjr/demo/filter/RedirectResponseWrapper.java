package com.yhjr.demo.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 重定向请求包装类
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月24日
 */
public class RedirectResponseWrapper extends HttpServletResponseWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectResponseWrapper.class);
    @SuppressWarnings("unused")
    private final HttpServletRequest request;
    
    @Value("${https.contextPath}")
    private String httpsContextPath ;

    public RedirectResponseWrapper(final HttpServletRequest inRequest, final HttpServletResponse response) {
        super(response);
        this.request = inRequest;
    }

    @Override
    public void sendRedirect(final String locationUrl) throws IOException {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("转换前RedirectUrl:locationUrl={}",locationUrl);
        }
        if (StringUtils.isBlank(locationUrl)) {
            super.sendRedirect(locationUrl);
            return;
        }

        try {
            final URI uri = new URI(locationUrl);
            if (uri.getScheme() != null) {
                super.sendRedirect(locationUrl);
                return;
            }
        } catch (URISyntaxException ex) {
            super.sendRedirect(locationUrl);
        }

        // !!! FIX Scheme !!!
        /*String finalurl = "https://" + this.request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            finalurl += ":" + request.getServerPort();
        }
        finalurl += locationUrl;*/
//        String finalurl =httpsContextPath+ locationUrl;
        String finalurl ="https://10.0.12.26"+ locationUrl;
        LOGGER.info("转换前RedirectUrl:httpsContextPath={}",httpsContextPath);
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("转换前RedirectUrl:finalurl={}",finalurl);
        }
        super.sendRedirect(finalurl);
    }

}