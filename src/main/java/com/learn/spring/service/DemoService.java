package com.learn.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.spring.bean.Demo;
import com.learn.spring.dao.DemoMapper;

@Service
public class DemoService {
	@Resource
	private DemoMapper demoMapper;

	@Transactional
	public void save(Demo demo) {
		demoMapper.insert(demo);
	}

	public Demo getUserById(long id) {
		return demoMapper.getDemoById(id);
	}

	public List<Demo> getAllUser() {
		return demoMapper.getAllDemo();
	}

	@Transactional(rollbackFor = Exception.class)
	public void inserUser(String name) {
		demoMapper.insert(new Demo(name));
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(long id) {
		demoMapper.delete(id);
	}
}
