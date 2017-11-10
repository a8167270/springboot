package com.learn.spring.entity;

import com.learn.spring.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
public class  Result {
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

	public Result() {
		code = 0;
		msg = "请求成功！";
	}

    public static ResponseEntity<Result> success(){
        return new ResponseEntity<Result>(new Result(), HttpStatus.OK);
    }

   public static ResponseEntity<Result> success(Object data){
	    return new ResponseEntity<Result>(new Result(data), HttpStatus.OK);
   }

    public static ResponseEntity<Result> error(ResultCode resultCode, HttpStatus status){
        return new ResponseEntity<Result>(new Result(resultCode), status);
    }
}
