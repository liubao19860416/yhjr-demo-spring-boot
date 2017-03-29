package com.yhjr.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.yhjr.demo.vo.ResultInfo;

/**
 * Rest服务接口测试类
 * 
 * @author LiuBao
 * @version 2.0
 * 2017年3月27日
 *
 */
public class RestServiceTests extends BaseRestServiceTests {
	
    @SuppressWarnings("rawtypes")
	@Test
    public void testListTestEntitys() {
    	String requestUrl="";
        RestTemplate restTemplate = getRestTemplate(requestUrl);
        ResponseEntity<ResultInfo> entity = restTemplate.getForEntity(baseUrl + "/ajax/listTestEntitys?username={username}&password={password}", ResultInfo.class, "LiuBao", "123456");
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        ResultInfo resultInfo = entity.getBody();
        
        assertNotNull(resultInfo);
        assertNotNull(resultInfo.getData());
        assertNotNull("0", resultInfo.getCode());
        Assert.assertNull(resultInfo.getMessage());
    }
    
}
