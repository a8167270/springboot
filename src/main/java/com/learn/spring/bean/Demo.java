package com.learn.spring.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ApiModel(value="用户")
@Entity(name="user")
public class Demo {

	@Id @GeneratedValue
	private long id;// 主键.
	
	@ApiModelProperty("姓名")
	@NotNull(message = "name不能为null")
	private String name;// 测试名称
	
	public Demo(String name) {
		this.name = name;
	}
}
