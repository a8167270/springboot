package com.learn.spring.service;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.learn.spring.bean.Demo;
import com.learn.spring.dao.DemoDao;
import com.learn.spring.dao.DemoRepository;

@Service
public class DemoService {
	@Resource
	private DemoRepository demoRepository;

	@Resource
	private DemoDao demoDao;

	@Transactional
	public void save(Demo demo) {
		demoRepository.save(demo);
	}

	public Demo getById(long id) {
		// demoRepository.findOne(id);//在demoRepository可以直接使用findOne进行获取.
		return demoDao.getById(id);
	}
}
