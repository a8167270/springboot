package com.learn.spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.bean.Demo;
import com.learn.spring.bean.SelfProperties;
import com.learn.spring.service.DemoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/users")
@EnableSwagger2
public class Example {
	@Resource
	private SelfProperties property;

	@Resource
	private DemoService demoService;

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	String home(long id) {
		List<Demo> demos = demoService.getAllUser();

		StringBuilder builder = new StringBuilder(property.getVersion() + property.getDescription());
		demos.stream().forEach(x -> {
			builder.append(x.getId() + " " + x.getName() + "\n");
		});

		return builder.toString();
	}
}