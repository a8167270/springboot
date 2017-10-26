package com.learn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.bean.Demo;
import com.learn.spring.bean.Result;
import com.learn.spring.exception.ErrorResponse;
import com.learn.spring.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/demo")
@Api("user")
public class DemoController {
	@Autowired
	private DemoService demoService;

	@ApiOperation(value = "获取用户", notes = "根据url的id来获取对象", response = Demo.class)
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDemo(@ApiParam(value = "用户id", required = true) @PathVariable long id) {

		Demo demo = demoService.getUserById(id);
		HttpStatus status = demo == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		if (demo == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(4, id + " is not found"), status);
		}

		return new ResponseEntity<Demo>(demo, status);
	}

	@ApiOperation(value = "获取所有对象", notes = "获取所有对象", response = Demo.class, responseContainer = "List")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Result> getDemos() {
		List<Demo> demos = demoService.getAllUser();

		return new ResponseEntity<Result>(new Result(demos), HttpStatus.OK);
	}

	@ApiOperation(value = "保存用户", notes = "保存对象")
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<Result> save(@RequestBody @Validated Demo demo) {
		demoService.inserUser(demo);// 保存数据

		return new ResponseEntity<Result>(new Result(demo), HttpStatus.OK);
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来删除对象")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteDemo(@PathVariable long id) {
		demoService.deleteUser(id);// 保存数据.
		return "ok.Demo2Controller delete test";
	}
}
