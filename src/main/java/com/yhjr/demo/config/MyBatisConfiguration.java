package com.yhjr.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * 扫描定义类
 * 
 * @author LiuBao
 * @version 2.0
 * 2017年3月27日
 *
 */
@Configuration
@MapperScan(basePackages = "com.yhjr.demo.mapper")
public class MyBatisConfiguration {

    @Value("${mybatis.config.location:/mybatis-config.xml}")
    private Resource configLocation;

    @Bean
    public SqlSessionFactory buildSqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(configLocation);
        return sessionFactory.getObject();
    }
    
}
