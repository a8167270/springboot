package com.learn.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.learn.spring.bean.Demo;

@Mapper
public interface DemoMapper {
	
	@Select("select * from user where id = #{id}")
	Demo getDemoById(long id);
	
	@Select("select * from user")
	List<Demo> getAllDemo();
	
    @Insert("INSERT INTO user(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Demo user);
    
    @Delete("delete from user where id = #{id}")
    void delete(long id);
}
