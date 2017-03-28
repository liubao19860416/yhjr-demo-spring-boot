package com.yhjr.demo.service;

import java.util.List;

import com.yhjr.demo.domain.TestEntity;

/**
 * Service层测试接口定义
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
public interface TestEntityService {
	
	/**
	 * 列表查询接口定义
	 */
	List<TestEntity> findAllTestEntitys();

	/**
	 * 添加实体信息接口定义
	 */
	boolean addTestEntity(TestEntity testEntity);
	
}
