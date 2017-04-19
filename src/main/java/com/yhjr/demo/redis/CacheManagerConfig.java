//package com.yhjr.demo.redis;
//
//import java.lang.reflect.Method;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//
///**
// * CacheManager封装类
// * 
// * @Author  LiuBao
// * @Version 2.0
// *   2017年4月5日
// */
//@Configuration
//@EnableCaching
//@PropertySource(value = "classpath:/redis.properties")
//public class CacheManagerConfig extends CachingConfigurerSupport {
//    
//    private static final Logger LOGGER = LoggerFactory.getLogger(CacheManagerConfig.class);
//    
////    @Value("${spring.redis.host}")
////    private String host;
////
////    @Value("${spring.redis.port}")
////    private int port;
//    
////    @Value("${spring.redis.pool.max-idle}")
////    private int maxIdle;
////
////    @Value("${spring.redis.pool.max-wait}")
////    private long maxWaitMillis;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//    
////    @Value("${spring.redis.password}")
////    private String password;
////
//
////    /**
////     *  Jedis连接池
////     */
////    @Bean
////    public JedisPool redisPoolFactory() {
////        LOGGER.info("JedisPool注入成功！！");
////        LOGGER.info("redis地址：" + host + ":" + port);
////        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
////        jedisPoolConfig.setMaxIdle(maxIdle);
////        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
////
////         JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port,timeout, password);
//////        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
////
////        return jedisPool;
////    }
//
//    // JedisCluster
//
////    @Bean
////    public JedisCluster JedisClusterFactory(JedisPoolConfig jedisPoolConfig) {
////        LOGGER.info("JedisCluster创建！！");
////        LOGGER.info("redis地址：" + host + ":" + port);
////        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
////       // JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
////        jedisPoolConfig.setMaxIdle(maxIdle);
////        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
////        jedisClusterNodes.add(new HostAndPort(host, port));
////        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, jedisPoolConfig);
////        return jedisCluster;
////    }
////    
//    
//    
//    
////    @Bean
////    public KeyGenerator wiselyKeyGenerator() {
////        return new KeyGenerator() {
////            @Override
////            public Object generate(Object target, Method method, Object... params) {
////                StringBuilder sb = new StringBuilder();
////                sb.append(target.getClass().getName());
////                sb.append(method.getName());
////                for (Object obj : params) {
////                    sb.append(obj.toString());
////                }
////                LOGGER.debug("生成对应redis-key为:{}",sb.toString());
////                return sb.toString();
////            }
////        };
////    }
//    
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        // 设置key-value超时时间
//        //cacheManager.setDefaultExpiration(10); 
//        return cacheManager;
//    }
//    
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
//        LOGGER.debug("注入的超时时间为:{}",timeout);
//        factory.setTimeout(timeout); 
//        return factory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        // 设置序列化工具，这样ReportBean不需要实现Serializable接口
//        setSerializer(template); 
//        template.afterPropertiesSet();
//        LOGGER.debug("RedisTemplate初始化结束...");
//        return template;
//    }
//
//    private void setSerializer(StringRedisTemplate template) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
//                Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        LOGGER.debug("模版设置结束...");
//    }
//}