package com.learn.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.learn.spring.bean.Demo;

public interface DemoRepository extends CrudRepository<Demo, Long> {

}
