package com.yhjr.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yhjr.demo.controller.MyGlobalController;
import com.yhjr.demo.filter.BasicAuthorizeAttributeFilter;

/**
 * SpringBoot启动类
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication implements CommandLineRunner,EmbeddedServletContainerCustomizer {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	// @Value("${name}")
	@Value("${spring.profiles:Dev-}")
	private String name;
	
	@Value("${server.port}")
	private Integer port;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("Name is :: : {}", name);
		log.info("DemoApplication finished start...");
		Thread.currentThread().join();
	}

	/**
	 * 修改启动端口
	 * 方法1:application.properties添加server.port=8089配置;
	 * 方法2:当前位置设置;
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		//configurableEmbeddedServletContainer.setPort(8088);
		log.info("修改启动端口{}", port);
	}
	
	/**
	 * 设置异常错误处理信息
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	        	ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, MyGlobalController.ERROR_400);
	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, MyGlobalController.ERROR_401);
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, MyGlobalController.ERROR_404);
	            ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, MyGlobalController.ERROR_405);
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, MyGlobalController.ERROR_500);
	            container.addErrorPages(error400Page,error401Page, error404Page,error405Page, error500Page);
	            log.info("设置异常错误处理信息结束...");
	        }
	    };
	}
	
    
    @Bean  
    public FilterRegistrationBean  filterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        BasicAuthorizeAttributeFilter httpBasicFilter = new BasicAuthorizeAttributeFilter();  
        registrationBean.setFilter(httpBasicFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/user/*");  
        urlPatterns.add("/sys/*");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    } 
    
}
