package com.learn.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.learn.spring.bean.Demo;

@Repository
public class DemoDao {
	@Autowired  
	private JdbcTemplate jdbcTemplate;

	/**
	 * 通过id获取demo对象.
	 * 
	 * @param id
	 * @return
	 */
	public Demo getById(long id) {
		String sql = "select * from user where id=?";

		RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<Demo>(Demo.class);
		return jdbcTemplate.queryForObject(sql, rowMapper, id);

	}
}
