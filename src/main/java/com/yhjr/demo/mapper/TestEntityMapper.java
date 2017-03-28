package com.yhjr.demo.mapper;

import java.util.List;

import com.yhjr.demo.domain.TestEntity;

/**
 * 测试实体对应的Mapper
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
public interface TestEntityMapper {

	/**
	 * 列表数据查询测试接口
	 */
	List<TestEntity> findAllTestEntitys();

	/**
	 * 添加实体信息接口
	 */
	int insertTestEntity(TestEntity testEntity);
	
}
