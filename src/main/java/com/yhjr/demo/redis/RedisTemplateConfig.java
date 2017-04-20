package com.yhjr.demo.redis;
//package com.yh.loan.front.redis;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.yh.loan.front.config.RedisClusterProperties;
//
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//import redis.clients.jedis.ShardedJedisPool;
//
///**
// * JedisCluster配置文件
// * 
// * @Author  LiuBao
// * @Version 2.0
// *   2017年4月1日
// */
//@Configuration
//public class RedisTemplateConfig {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTemplateConfig.class);
//    
//    @Autowired
//    private RedisClusterProperties redisClusterProperties;
//    
//    @Autowired
//    private JedisPoolConfig jedisPoolConfig;
//
//    @Bean(name = "shardedJedisPool")
//    public ShardedJedisPool initShardedJedisPool() {
//        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");
//         List<JedisShardInfo> shardInfos=new ArrayList<>();
//         for (String ipPort : serverArray) {
//             String[] ipPortPair = ipPort.split(":");
//             JedisShardInfo shardInfo=new JedisShardInfo(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()));
//             shardInfos.add(shardInfo);
//         }
//        ShardedJedisPool shardedJedisPool=new ShardedJedisPool(jedisPoolConfig, shardInfos);
////        ShardedJedis jedis = shardedJedisPool.getResource();
////        jedis.set("z", "bar");
////        jedis.close();
//        LOGGER.debug("注入的超时时间为:{}", redisClusterProperties.getConnectionTimeout());
//        return shardedJedisPool;
//    }
//    
//    @Bean(name = "redisConnectionFactory")
//    public JedisConnectionFactory initJedisConnectionFactory() {
//        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");
//        List<JedisShardInfo> shardInfos=new ArrayList<>();
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            JedisShardInfo shardInfo=new JedisShardInfo(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()));
//            shardInfos.add(shardInfo);
//        }
//        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
//        factory.setHostName("192.168.52.132");  
//        factory.setPort(7000);  
//        factory.setUsePool(true);
//        factory.setTimeout(redisClusterProperties.getConnectionTimeout());
//        LOGGER.debug("注入的超时时间为:{}", redisClusterProperties.getConnectionTimeout());
//        return factory;
//    }
//    
//    @Bean(name = "redisConnectionFactory2")
//    public JedisConnectionFactory initJedisConnectionFactory2() {
//        HashSet<RedisNode> sentinels=new LinkedHashSet<RedisNode>();
//        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");
//         for (String ipPort : serverArray) {
//             String[] ipPortPair = ipPort.split(":");
//             sentinels.add(new RedisNode(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//         }
//         
//         RedisSentinelConfiguration redisSentinelConfiguration=new RedisSentinelConfiguration();
//        redisSentinelConfiguration.setSentinels(sentinels);
//        RedisNode master=new RedisNode(serverArray[0].split(":")[0].trim(), Integer.valueOf(serverArray[0].split(":")[1].trim()));
//        master.setName("cluster-redis0");
//        redisSentinelConfiguration.setMaster(master);
//        
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisSentinelConfiguration,jedisPoolConfig);
//        factory.setHostName("192.168.52.132");  
//        factory.setPort(7000);  
//        factory.setUsePool(true);
//        
//        LOGGER.debug("注入的超时时间为:{}", redisClusterProperties.getConnectionTimeout());
//        factory.setTimeout(redisClusterProperties.getConnectionTimeout());
//        return factory;
//    }
//
//    @Bean(name = "redisTemplate2")
//    public RedisTemplate<String, String> initRedisTemplate2() {
//        RedisTemplate<String, String> template = new RedisTemplate<String, String>();  
//        template.setConnectionFactory(initJedisConnectionFactory());  
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        return template;
//    }
//    
//    @Bean(name = "redisTemplate")
//    public RedisTemplate<String, String> initRedisTemplate() {
//        StringRedisTemplate template = new StringRedisTemplate(initJedisConnectionFactory());
//        // 设置序列化工具，这样ReportBean不需要实现Serializable接口
//        setSerializer(template);
//        template.afterPropertiesSet();
//        LOGGER.debug("RedisTemplate初始化结束...");
//        return template;
//    }
//
//    private void setSerializer(StringRedisTemplate template) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        LOGGER.debug("Serializer模版设置StringRedisTemplate结束...");
//    }
//    
//}