package com.yhjr.demo.redis;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.JedisCluster;

/**
 *  RedisCacheService服务定义接口实现类
 *  
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月10日
 */
@Repository("redisCacheService")
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private JedisCluster jedisCluster;
    
    public <T> String addByKey(String key, T object) throws IOException {
        String object2JsonString = JSON.toJSONString(object);
        String set = jedisCluster.set(key, object2JsonString);
        return set;
    }
    
    @Override
    public <T> Long addListKey(Map<String, T> map) throws IOException {
        Long sum = (long) 0;
        Iterator<Entry<String, T>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, T> entry = iterator.next();
            String key = entry.getKey();
            T object = entry.getValue();
            addByKey(key, object);
            sum = sum + 1;
        }
        return sum;
    }
    
    /*@Override
    public <T> Long addListKey(Map<String, T> map) throws IOException {
        Long sum = (long) 0;
        Iterator<Entry<String, T>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, T> entry = iterator.next();
            String key = entry.getKey();
            T object = entry.getValue();
            addByKey(key, object);
            sum = sum + 1;
        }
        return sum;
    }*/
    
    @Override
    public Object getObject(String key) throws IOException {
        return jedisCluster.get(key);
//        return getObject(key, Object.class);
    }
    
    @Override
    public  <V> V getObject(String key,Class<V> clazz) throws IOException {
        String value = jedisCluster.get(key);
        V json2Object = JSON.parseObject(value, clazz);
        return json2Object;
    }

    @Override
    public Long deleteByKey(String key) throws IOException {
        Long del = jedisCluster.del(key);
        return del;
    }

    @Override
    public Long batchDelete(List<String> keyList) throws IOException {
        Long sum = (long) 0;
        Long del = (long) 0;
        for (int i = 0; i < keyList.size(); i++) {
            del = jedisCluster.del(keyList.get(i));
            sum = sum + del;
        }
        return sum;
    }

}