//package com.yh.loan.front.redis;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.NamedNode;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SetOperations;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//
///**
// * 基础的REDIS服务类
// * 
// * @Author  LiuBao
// * @Version 2.0
// *   2017年4月11日
// */
//@Configuration("redisCacheDAO")
////@Component("redisCacheDAO")
//public class RedisCacheDAO {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
//
//    @Resource(name="redisTemplate")
//    protected RedisTemplate<String, String> redisTemplate;
//    
//    @Autowired
//    @Qualifier("redisClusterProperties")
//    private RedisClusterProperties redisClusterProperties;
//    
//    /*public void put(String key, String hashKey, Map<String, Object> value) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//    }
//    
//    public Boolean putIfAbsent(String key, String hashKey, Object value) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.putIfAbsent(key, hashKey, value);
//    }*/
//
//    public void put(String key, String hashKey, Object value) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//    }
//
//    public void putAll(String key, Map<String, Object> value) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        hash.putAll(key, value);
//    }
//
//    public Map<String, Object> entries(String key) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.entries(key);
//    }
//
//    public Object get(String key, String hashKey) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.get(key,hashKey);
//    }
//
//    public void flushDb() {
//        redisTemplate.getConnectionFactory().getConnection().flushDb();
//        LOGGER.warn("redis flushDb is finished.");
//    }
//
//    public void delete(String key, Object hashKey) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        hash.delete(key, hashKey);
//    }
//
//    public Collection<Object> multiGet(String key, Collection<String> hashKeys) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.multiGet(key, hashKeys);
//    }
//
//    public Set<String> keys(String key) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.keys(key);
//    }
//
//    public Long size(String key) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.size(key);
//    }
//
//    public Collection<Object> values(String key) {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.values(key);
//    }
//
//    public RedisOperations<String, ?> getOperations() {
//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//        return hash.getOperations();
//    }
//
//    public Long addSetValues(String key, String ... values) {
//        SetOperations<String, String> set = redisTemplate.opsForSet();
//        return set.add(key, values);
//    }
//
//    public String getSetValue(String key) {
//        SetOperations<String, String> set = redisTemplate.opsForSet();
//        return set.pop(key);
//    }
//
//    public void delSetValue(String key, Object hashKey) {
//        SetOperations<String, String> set = redisTemplate.opsForSet();
//        set.remove(key, hashKey);
//    }
//    
//    public boolean setNX(final String key, final String value) {
////      return redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), value.getBytes());
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
//                return connection.setNX(key.getBytes(), value.getBytes());  
//            }  
//        });  
//        LOGGER.info("redis setNX result is :{}",result);
//        return result;
//    }
//    
//    public boolean set(final String key, final String value) {
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection)  throws DataAccessException {  
//                connection.set(key.getBytes(), value.getBytes());  
//                return true;
//            }  
//        });  
//        LOGGER.info("redis set result is :{}",result);
//        return result;
//    }
//    
//    public String getValue(final String key) {
////      System.out.println("getvalue:" + redisTemplate.getConnectionFactory().getConnection().get(key.getBytes()));
////      return new String(redisTemplate.getConnectionFactory().getConnection().get(key.getBytes()));
//        String result = redisTemplate.execute(new RedisCallback<String>() {  
//            public String doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//                return new String(connection.get(key.getBytes()));
//            }  
//        });  
//        LOGGER.info("redis getValue:key={}, result is :{}",key,result);
//        return result;
//    }
//    
//    public boolean expire(final String key, final Long seconds) {
//        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
//                connection.expire(key.getBytes(), seconds);
//                return true;
//            }  
//        });  
//        return result;
//    }
//    
//    public boolean delValue(final String key) {
//        //redisTemplate.getConnectionFactory().getConnection().del(key.getBytes());
//        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//            public Boolean doInRedis(RedisConnection connection)throws DataAccessException {  
//                connection.del(key.getBytes()); 
//                return true;
//            }  
//        });  
//        return result;
//    }
//    
//    @Bean(name="redisClusterProperties")
//    public RedisClusterProperties redisClusterProperties(){
//        return new RedisClusterProperties();
//    }
//
//    @Autowired
//    @Bean(name = "redisConnectionFactory")
//    public JedisConnectionFactory redisConnectionFactory(RedisClusterProperties redisClusterProperties) {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(redisClusterProperties.getPoolMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(redisClusterProperties.getPoolMaxWait());
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisClusterProperties.getPoolTimeBetweenEvictionRunsMillis());
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisClusterProperties.getPoolMinEvictableIdleTimeMillis());
//        jedisPoolConfig.setTestOnBorrow(redisClusterProperties.isPoolTestOnBorrow());
//        jedisPoolConfig.setMaxIdle(redisClusterProperties.getPoolMaxIdle());
//        
//        RedisSentinelConfiguration redisSentinelConfiguration=new RedisSentinelConfiguration();
//        HashSet<RedisNode> sentinels=new LinkedHashSet<RedisNode>();
//        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");
//         for (String ipPort : serverArray) {
//             String[] ipPortPair = ipPort.split(":");
//             sentinels.add(new RedisNode(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
////             
//         }
//         
////         JedisShardInfo shardInfo=new  ;
//        redisSentinelConfiguration.setSentinels(sentinels);
//        RedisNode master=new RedisNode(serverArray[0].split(":")[0].trim(), Integer.valueOf(serverArray[0].split(":")[1].trim()));
//        master.setName("cluster-redis0");
//        redisSentinelConfiguration.setMaster(master);
//        
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisSentinelConfiguration,jedisPoolConfig);
//        LOGGER.debug("注入的超时时间为:{}", redisClusterProperties.getConnectionTimeout());
//        factory.setTimeout(redisClusterProperties.getConnectionTimeout());
//        return factory;
//    }
//
//    @Autowired
//    @Bean(name = "redisTemplate")
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
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
//        LOGGER.debug("Serializer模版设置StringRedisTemplate结束...");
//    }
//    
//}
