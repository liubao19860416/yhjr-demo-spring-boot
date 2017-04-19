package com.yhjr.demo.redis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * RedisDAO服务定义接口类
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月5日
 */
public interface RedisDAO {

    <T> String addByKey(String key, T object) throws IOException;

    <T> Long  addListKey(Map<String, T> map) throws IOException;

    Object getObject(String key) throws IOException;

    <T> T getObject(String key, Class<T> clazz) throws IOException;
    
    Long deleteByKey(String key) throws IOException;
    
    Long batchDelete(List<String> keyList) throws IOException;

}
