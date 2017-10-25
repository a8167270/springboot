package com.learn.spring.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name="user")
public class Demo {

	@Id @GeneratedValue
	private long id;// 主键.
	
	@NotNull
	private String name;// 测试名称
	
	public Demo(String name) {
		this.name = name;
	}
}
