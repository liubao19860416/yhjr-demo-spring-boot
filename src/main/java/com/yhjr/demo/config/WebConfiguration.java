package com.yhjr.demo.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yhjr.demo.interceptor.LoginAuthInterceptor;
import com.yhjr.demo.interceptor.TimeInteceptor;

/**
 * WebMVC配置定义信息加载
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    
    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

	@Bean
	public FastJsonHttpMessageConverter customFastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
		jsonConverter.setCharset(Charset.forName("UTF-8"));
		jsonConverter.setFeatures(SerializerFeature.WriteDateUseDateFormat);
		List<MediaType> supportedMediaTypes=new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customFastJsonHttpMessageConverter());
		super.addDefaultHttpMessageConverters(converters);
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initTimeInteceptor()).addPathPatterns("/**")
                                            .excludePathPatterns("/static/**")
                                            .excludePathPatterns("**.jpg")
                                            .excludePathPatterns("/error/**");
        registry.addInterceptor(initLoginAuthInterceptor()).addPathPatterns("/**")
                                            .excludePathPatterns("/static/**")
                                            .excludePathPatterns("**.jpg")
                                            .excludePathPatterns("/error/**");
        logger.warn("MyWebAppConfigurer初始化执行了...");
        super.addInterceptors(registry);
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
            .addResourceLocations("classpath:/css/")
            .addResourceLocations("classpath:/js/");
        super.addResourceHandlers(registry);
    }

	@Bean
    public LoginAuthInterceptor initLoginAuthInterceptor() {
        return new LoginAuthInterceptor();
    }
	
	@Bean
	public TimeInteceptor initTimeInteceptor() {
	    return new TimeInteceptor();
	}

}
