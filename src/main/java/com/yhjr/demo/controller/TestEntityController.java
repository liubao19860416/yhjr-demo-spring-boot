package com.yhjr.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhjr.demo.domain.TestEntity;
import com.yhjr.demo.service.TestEntityService;
import com.yhjr.demo.vo.TestParam;
import com.yhjr.demo.vo.TestResultVO;

/**
 * 测试Controller层接口定义
 * 
 * @author LiuBao
 * @version 2.0 2017年3月27日
 * 
 */
@Controller
@RequestMapping("/test")
public class TestEntityController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestEntityController.class);

	@Autowired
	private TestEntityService testEntityService;

	/**
	 * 页面跳转测试
	 */
	@RequestMapping("/ui/listTestEntitys")
	public String listTestEntitys(Model model) {
		List<TestEntity> testEntitys = testEntityService.findAllTestEntitys();
		model.addAttribute("testEntitys", testEntitys);
		model.addAttribute("username", "LiuBao");
		return "/test/listTestEntitys";
	}

	/**
	 * 数据列表查询测试
	 */
	@ResponseBody
	@RequestMapping("/ajax/listTestEntitys")
	public Object listTestEntitys(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		LOGGER.debug("username:{}, password:{}", username, password);
		List<TestEntity> testEntitys = testEntityService.findAllTestEntitys();
		return testEntitys;
	}
	
	/**
	 * 数据列表查询测试
	 */
	@ResponseBody
	@RequestMapping("/ajax/addTestEntity")
	public Object addTestEntity(@RequestBody TestParam testParam) {
		LOGGER.debug("testParam:{}", testParam);
		TestEntity testEntity=new TestEntity();
		testEntity.setUserName("username"+testParam.getUserId());
		testEntity.setPassword(UUID.randomUUID().toString());
		testEntity.setType("1");
		boolean result = testEntityService.addTestEntity(testEntity);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		if(result){
			resultMap.put("code", 0);
			resultMap.put("data", testEntity);
		}else{
			resultMap.put("code", -1);
			resultMap.put("data", result);
		}
		return resultMap;
	}

	/**
	 * 数据实体查询测试
	 */
	@ResponseBody 
	@RequestMapping("/ajax/listTestResultVO")
	public Object listTestResultVO(@RequestBody TestParam testParam) {
		LOGGER.debug("testParam:{}", testParam);
		TestResultVO testResultVO = new TestResultVO();
		testResultVO.setUserName(testParam.getUserId());
		testResultVO.setPassword(testParam.getPassword());
		testResultVO.setAge(30);
		testResultVO.setTestParam(testParam);
		return testResultVO;
	}

}
