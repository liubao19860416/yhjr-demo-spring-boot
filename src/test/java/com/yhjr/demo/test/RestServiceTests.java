package com.yhjr.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.yhjr.demo.vo.TestResultVO;

/**
 * Rest服务接口测试类
 * 
 * @author LiuBao
 * @version 2.0
 * 2017年3月27日
 *
 */
public class RestServiceTests extends BaseRestServiceTests {
	
    @Test
    public void testListTestEntitys() {
        RestTemplate rest = getRestTemplate();
        ResponseEntity<TestResultVO> entity = rest.getForEntity(baseUrl + "/ajax/listTestEntitys?username={username}&password={password}", TestResultVO.class, "LiuBao", "123456");
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        TestResultVO testResultVO = entity.getBody();

        assertNotNull(testResultVO);
        assertNotNull(testResultVO.getAge());
        assertNotNull("LiuBao", testResultVO.getUserName());
        assertNotNull("123456", testResultVO.getPassword());
    }
}
