//package com.yhjr.demo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 
// * @Author LiuBao
// * @Version 2.0 2017年3月28日
// */
//@Configuration
//@ComponentScan
//@EnableCaching
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class, })
//public class Application {
//	
//	public static void main(String[] args) throws Exception {
//		SpringApplication app = new SpringApplication(Application.class);
//		app.run(args);
//	}
//	
//}
