package com.learn.spring.filter;



import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;


@Aspect
@Configuration
public class LoggerAopConfig {
	private ObjectMapper mapper = new ObjectMapper();
    protected final Logger log = LoggerFactory.getLogger(this.getClass());  

	@Pointcut("execution(public * com.learn.spring.controller.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String loginfo = request.getMethod() + " : " + request.getRequestURL().toString() ;
		if(request.getMethod().equals("POST") || request.getMethod().equals("PUT")){
			loginfo = loginfo +  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(joinPoint.getArgs());
		}
		
		// 记录下请求内容
		log.info(loginfo);
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		log.info("RESPONSE : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ret));
	}

}
