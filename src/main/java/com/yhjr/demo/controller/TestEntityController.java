package com.yhjr.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yhjr.demo.domain.TestEntity;
import com.yhjr.demo.exception.ErrorCodeConstant;
import com.yhjr.demo.service.TestEntityService;
import com.yhjr.demo.utils.MessageSourceUtil;
import com.yhjr.demo.vo.ResultInfo;
import com.yhjr.demo.vo.TestParam;

import redis.clients.jedis.JedisCluster;

/**
 * 测试Controller层接口定义
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
@Controller
@RequestMapping("/test")
public class TestEntityController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestEntityController.class);

	@Autowired
	private TestEntityService testEntityService;
	
	@Autowired
	private MessageSourceUtil messageSourceUtil;
	
	@Autowired
    private JedisCluster   jedisCluster ;
	
	/**
	 * 页面跳转测试
	 */
	@RequestMapping("/ui/listTestEntitys")
	public String listTestEntitys(Model model) {
		List<TestEntity> testEntitys = testEntityService.findAllTestEntitys();
		model.addAttribute("testEntitys", testEntitys);
		model.addAttribute("username", jedisCluster.get("username"));
		model.addAttribute("message", messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_DEFAULT));
		return "/test/listTestEntitys";
	}
	
	/**
	 * 数据列表查询测试
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/listTestEntitys")
	public Object listTestEntitys(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) {
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		LOGGER.debug("username:{}, password:{}", username, password);
		List<TestEntity> testEntitys = testEntityService.findAllTestEntitys();
		return new ResultInfo<List<TestEntity>>().buildSuccess(testEntitys);
	}
	
	/**
	 * 数据列表查询测试
	 */
	@ResponseBody
	@RequestMapping(value ="/ajax/addTestEntity" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addTestEntity(@Validated @RequestBody TestParam testParam) {
		LOGGER.debug("testParam:{}", testParam);
		TestEntity testEntity=new TestEntity();
		testEntity.setUserName("username"+testParam.getUserId());
		testEntity.setPassword(UUID.randomUUID().toString());
		testEntity.setType("1");
		boolean result = testEntityService.addTestEntity(testEntity);
		if(result){
			return new ResultInfo<TestEntity>().buildSuccess(testEntity);
		}else{
			ResultInfo<Boolean> resultInfo = new ResultInfo<Boolean>()
					.buildFailure(ErrorCodeConstant.ERROR_CODE_DEFAULT,
							messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_DEFAULT));
			resultInfo.setData(result);
			return resultInfo;
		}
	}
	
	@ResponseBody
	@RequestMapping(value ="/ajax/addTestEntity2" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addTestEntity2(@RequestBody TestParam testParam) {
	    LOGGER.debug("testParam:{}", testParam);
	    TestEntity testEntity=new TestEntity();
	    testEntity.setUserName("username"+testParam.getUserId());
	    testEntity.setPassword(UUID.randomUUID().toString());
	    testEntity.setType("1");
	    testEntity.setDelFlag(Boolean.FALSE);
	    boolean result = testEntityService.addTestEntity(testEntity);
	    if(result){
	        return new ResultInfo<TestEntity>().buildSuccess(testEntity);
	    }else{
	        ResultInfo<Boolean> resultInfo = new ResultInfo<Boolean>()
	                .buildFailure(ErrorCodeConstant.ERROR_CODE_DEFAULT,
	                        messageSourceUtil.getMessage(ErrorCodeConstant.ERROR_CODE_DEFAULT));
	        resultInfo.setData(result);
	        return resultInfo;
	    }
	}

	/**
	 * 数据实体查询测试
	 */
	@ResponseBody 
	@RequestMapping(value="/ajax/listTestResultVO",method={RequestMethod.POST})
	public Object listTestResultVO(@RequestBody TestParam testParam) {
		LOGGER.debug("testParam:{}", testParam);
		return new ResultInfo<TestParam>().buildSuccess(testParam);
	}
	
	/**
	 * 缓存查询测试
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @ResponseBody 
	@RequestMapping(value="/ajax/listCache")
	public Object listCache(@RequestBody Map requestMap) {
	    LOGGER.debug("requestMap:{}", JSON.toJSONString(requestMap));
	    String username = jedisCluster.get("username");
	    String name = jedisCluster.get("name");
	    requestMap.put("username", username);
	    requestMap.put("name", name);
	    return new ResultInfo<Map>().buildSuccess(requestMap);
	}
	
}
