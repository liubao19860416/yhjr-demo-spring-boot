package com.yhjr.demo.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisCluster配置文件
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月1日
 */
@Configuration
public class JedisClusterConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisClusterConfig.class);

    @Autowired
    private RedisClusterProperties redisClusterProperties;
    
    /**
    * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
    */
//    @Bean
//    public JedisCluster getJedisCluster() {
//        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//         for (String ipPort : serverArray) {
//             String[] ipPortPair = ipPort.split(":");
//             jedisClusterNodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//         }
//         
//         JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//         jedisPoolConfig.setMaxIdle(redisClusterProperties.getPoolMaxIdle());
//         jedisPoolConfig.setMaxWaitMillis(redisClusterProperties.getPoolMaxWait());
//         jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisClusterProperties.getPoolTimeBetweenEvictionRunsMillis());
//         jedisPoolConfig.setMinEvictableIdleTimeMillis(redisClusterProperties.getPoolMinEvictableIdleTimeMillis());
//         jedisPoolConfig.setTestOnBorrow(redisClusterProperties.isPoolTestOnBorrow());
//         jedisPoolConfig.setMaxIdle(redisClusterProperties.getPoolMaxIdle());
//         
//        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes,redisClusterProperties.getConnectionTimeout(),
//                redisClusterProperties.getMaxAttempts(),jedisPoolConfig);
//         return jedisCluster;
//        //return new JedisCluster(jedisClusterNodes, redisProperties.getCommandTimeout());
//    }

    @Bean
    public JedisCluster getJedisCluster() {
        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
         for (String ipPort : serverArray) {
             String[] ipPortPair = ipPort.split(":");
             jedisClusterNodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
         }
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes,redisClusterProperties.getConnectionTimeout(),
                redisClusterProperties.getMaxAttempts(),initJedisPoolConfig());
        LOGGER.debug("获取JedisCluster执行结束。。。");
         return jedisCluster;
        //return new JedisCluster(jedisClusterNodes, redisProperties.getCommandTimeout());
    }
    
    @Bean(name="jedisPoolConfig")
    public JedisPoolConfig initJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisClusterProperties.getPoolMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisClusterProperties.getPoolMaxWait());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisClusterProperties.getPoolTimeBetweenEvictionRunsMillis());
        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisClusterProperties.getPoolMinEvictableIdleTimeMillis());
        jedisPoolConfig.setTestOnBorrow(redisClusterProperties.isPoolTestOnBorrow());
        jedisPoolConfig.setMaxIdle(redisClusterProperties.getPoolMaxIdle());
        return jedisPoolConfig;
    }
    
}