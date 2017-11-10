package com.learn.spring.entity;

import com.learn.spring.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ApiModel(value="用户")
public class User implements Serializable{
	private long id;// 主键.
	
	@ApiModelProperty("姓名")
	@NotNull(message = "name不能为null")
	private String name;// 测试名称
	private String pwd;
	private RoleEnum role;

	public User(String name) {
		this.name = name;
	}
}
