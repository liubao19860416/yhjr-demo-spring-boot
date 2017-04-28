package com.yhjr.demo.multiple;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

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
@AutoConfigureAfter({ DataSourceConfiguration.class })
public class MyBatisConfiguration {
    
//    @Value("${mybatis.mapper.location}")
//    private Resource mapperLocation;
//    @Value("${mybatis.mapper.locations}")
//    private Resource[] mapperLocations;
    
//    @Value("${mybatis.config.location:/mybatis-config.xml}")
    @Value("${mybatis.config.location}")
    private Resource configLocation;
    
    @Value("${mybatis.mapper.locations}")
    private String mapperLocations;
    
    @Value("${mybatis.typealiases.package}")
    private String typeAliasesPackage;

//    @Bean
//    public SqlSessionFactory buildSqlSessionFactory(DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setConfigLocation(configLocation);
//        sessionFactory.setTypeAliasesPackage("com.yhjr.demo.domain");
//        return sessionFactory.getObject();
//    }

    @Bean
    public SqlSessionFactory sqlSessionFactorys(AbstractRoutingDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis的配置文件路径
        //sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setConfigLocation(configLocation);
        //设置数据源为动态数据源
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        //设置类型前缀包名，在mapper文件中就不用使用详细的包名了，直接使用类名。
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        
        //配置路径匹配器，获取匹配的文件
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath:**/mapper/*.xml"));
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(mapperLocations));
        
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }
    
}
