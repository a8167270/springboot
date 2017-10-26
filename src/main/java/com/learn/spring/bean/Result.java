package com.learn.spring.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
	private int code;
	private String msg;
	private Object data;

	public Result(Object data) {
		code = 0;
		msg = "请求成功！";
		this.data = data;
	}

	public Result(ResultCode resultCode, Object data) {
		this(resultCode);
		this.data = data;
	}

	public Result(ResultCode resultCode) {
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
	}
}
