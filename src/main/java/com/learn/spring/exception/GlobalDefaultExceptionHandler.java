package com.learn.spring.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.bean.Result;
import com.learn.spring.bean.ResultCode;


@RestController
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());  

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result defaultErrorHandler(MethodArgumentNotValidException e) {

		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		String tips = "参数不合法";
		if (errors.size() > 0) {
			tips = errors.get(0).getDefaultMessage();
		}
		
		Result result = new Result(ResultCode.PARAMETER_ERROR);
		result.setMsg(tips);
		
		return result;
	}

}