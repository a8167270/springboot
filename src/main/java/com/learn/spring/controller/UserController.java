package com.learn.spring.controller;

import com.learn.spring.config.MyShiroRealm;
import com.learn.spring.entity.Result;
import com.learn.spring.entity.User;
import com.learn.spring.enums.ResultCode;
import com.learn.spring.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shawn
 */
@RestController
@RequestMapping(value = "/user")
@Api("user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userservice) {
		this.userService = userservice;
	}

	@ApiOperation(value = "获取用户", notes = "根据url的id来获取对象", response = User.class)
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @RequiresRoles("admin")
	public ResponseEntity<?> getUser(@ApiParam(value = "用户id", required = true) @PathVariable long id) {

		User user = userService.getUserById(id);
		HttpStatus status = user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		if (user == null) {
		    return Result.error(ResultCode.PARAMETER_ERROR, status);
		}

		return new ResponseEntity<>(user, status);
	}

	@ApiOperation(value = "获取所有对象", notes = "获取所有对象", response = User.class, responseContainer = "List")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    @RequiresRoles("user")
	public ResponseEntity<Result> getUsers(int offset, int size) {
		List<User> users = userService.getAllUser(offset,size);

		return Result.success(users);
	}

	@ApiOperation(value = "保存用户", notes = "保存对象")
	@PostMapping(value = "/save")
    @RequiresRoles(value = {"user","admin"}, logical = Logical.OR)
	public ResponseEntity<Result> save(@RequestBody @Validated User user) {
		// 保存数据
		userService.insertUser(user);
		return Result.success(user);
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来删除对象")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return Result.success();
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update用户", notes = "根据url的id来删除对象")
	public ResponseEntity<Result> updateUser(@PathVariable long id,@RequestBody @Validated User user) {
		userService.updateUser(user);
		MyShiroRealm shiroRealm = new MyShiroRealm();

		return Result.success(user);
	}

}
