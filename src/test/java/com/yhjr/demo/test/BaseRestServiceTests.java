package com.yhjr.demo.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yhjr.demo.vo.ResultInfo;

/**
 * 基础测试类
 * 
 * @author LiuBao
 * @version 2.0
 * 2017年3月27日
 *
 */
public class BaseRestServiceTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRestServiceTests.class);

    protected RestTemplate restTemplate;

    protected String baseUrl = "http://localhost:8088/test";
    protected Map<String,Object> requestParam=new HashMap<>();
    protected String requestUrl ;
    protected String code;
    protected String message;

    @Before
    public void initRestTemplate() {
        HttpClient client = HttpClientBuilder.create().addInterceptorFirst(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                if (code != null) {
                    httpRequest.addHeader("X-ApiKey", code);
                }
                if (message != null) {
                    httpRequest.addHeader("X-AuthString", message);
                }
                httpRequest.setHeader("Content-Type", "application/json");
                //httpRequest.setHeader("Content-Type", MediaType.APPLICATION_JSON.toString());
            }
        }).build();
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setCharset(Charset.forName("utf-8"));
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(converter);

        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        restTemplate = new RestTemplate(factory);
        restTemplate.setMessageConverters(converters);
        
        //调用测试
        //postHttpRequest();
    }

    public RestTemplate getRestTemplate(String requestUrl,Map<String,Object> requestParam) {
    	this.requestUrl=requestUrl;
    	this.requestParam=requestParam;
        return restTemplate;
    }
    
    public RestTemplate getRestTemplate(String requestUrl) {
    	this.requestUrl=requestUrl;
    	return restTemplate;
    }

    @SuppressWarnings("rawtypes")
	protected void postHttpRequest() {
        ResponseEntity<ResultInfo> result = restTemplate.postForEntity(baseUrl+requestUrl, requestParam, ResultInfo.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        code = result.getBody().getCode();
        message = result.getBody().getMessage() + "-" + System.currentTimeMillis();

        LOGGER.debug("authenticated with apikey : {}, authString : {}", code, message);
    }
}
