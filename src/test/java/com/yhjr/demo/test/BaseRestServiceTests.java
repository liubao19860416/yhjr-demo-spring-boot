package com.yhjr.demo.test;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yhjr.demo.vo.TestParam;
import com.yhjr.demo.vo.TestResultVO;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

    protected String baseUrl = "http://localhost:8080";
    protected String requestUrl = baseUrl + "/test";
    protected String apiKey;
    protected String authString;

    @Before
    public void initRestTemplate() {
        HttpClient client = HttpClientBuilder.create().addInterceptorFirst(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                if (apiKey != null) {
                    httpRequest.addHeader("X-ApiKey", apiKey);
                }
                if (authString != null) {
                    httpRequest.addHeader("X-AuthString", authString);
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
        postHttpRequest(getUserId(), getPassword());
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getUserId() {
        return "1000";
    }

    public String getPassword() {
        return "123456";
    }
    public Long getAge() {
    	return 30L;
    }

    protected void postHttpRequest(String userId, String password) {
    	TestParam param = new TestParam();
        param.setPassword(password);
        param.setUserId(userId);

        ResponseEntity<TestResultVO> result = restTemplate.postForEntity(requestUrl, param, TestResultVO.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        apiKey = result.getBody().getPassword();
        authString = result.getBody().getUserName() + "-" + System.currentTimeMillis();

        LOGGER.debug("authenticated with apikey : {}, authString : {}", apiKey, authString);
    }
}
