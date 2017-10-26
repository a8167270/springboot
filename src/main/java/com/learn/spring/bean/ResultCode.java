package com.learn.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
	SUCCESS(0, "请求成功"),
    WARN(-1, "网络异常，请稍后重试"), 
    PARAMETER_ERROR(101,"参数错误");
	
	private int code;
	private String msg;
}
