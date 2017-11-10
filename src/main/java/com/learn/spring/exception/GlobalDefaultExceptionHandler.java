package com.learn.spring.exception;

import com.learn.spring.entity.Result;
import com.learn.spring.enums.ResultCode;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());  

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result defaultParameterErrorHandler(MethodArgumentNotValidException e) {

		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		String tips = "参数不合法";
		if (errors.size() > 0) {
			tips = errors.get(0).getDefaultMessage();
		}
		
		Result result = new Result(ResultCode.PARAMETER_ERROR);
		result.setMsg(tips);
		
		return result;
	}

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result defaultAuthorizedErrorHandler(Exception e) {
        Result result = new Result(ResultCode.NOT_VIP);
        result.setMsg("非vip会员");

        return result;
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result defaultUnAuthenticationErrorHandler(Exception e) {
        Result result = new Result(ResultCode.NOT_LOGIN);
        result.setMsg("用户未登录");

        return result;
    }

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result defaultErrorHandler(Exception e) {
		e.printStackTrace();
		Result result = new Result(ResultCode.PARAMETER_ERROR);
		result.setMsg("服务端内部错误");

		return result;
	}
}