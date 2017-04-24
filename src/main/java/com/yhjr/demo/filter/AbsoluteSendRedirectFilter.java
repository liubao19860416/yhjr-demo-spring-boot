package com.yhjr.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 通过该Filter拦截SendRedirect请求并固定跳转到HTTPS
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月24日
 */
public class AbsoluteSendRedirectFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        RedirectResponseWrapper redirectResponseWrapper = new RedirectResponseWrapper(request, response);
        filterChain.doFilter(request, redirectResponseWrapper);
    }

}