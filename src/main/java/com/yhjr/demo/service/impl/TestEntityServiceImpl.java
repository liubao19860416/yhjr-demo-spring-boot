package com.yhjr.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yhjr.demo.domain.TestEntity;
import com.yhjr.demo.mapper.TestEntityMapper;
import com.yhjr.demo.service.TestEntityService;

/**
 * Service层测试接口实现
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
@Component
public class TestEntityServiceImpl implements TestEntityService {
	
	@Autowired
	private TestEntityMapper testEntityMapper;

	/**
	 * 列表查询测试接口实现
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List<TestEntity> findAllTestEntitys() {
		return testEntityMapper.findAllTestEntitys();
	}

	/**
	 * 添加实体信息接口实现
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = false)
	public boolean addTestEntity(TestEntity testEntity) {
		int result=testEntityMapper.insertTestEntity(testEntity);
		return result==1;
	}
}
