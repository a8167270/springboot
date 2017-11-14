package com.learn.spring.controller;

import com.learn.spring.config.MyShiroRealm;
import com.learn.spring.entity.Result;
import com.learn.spring.entity.User;
import com.learn.spring.enums.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@EnableAutoConfiguration
@EnableSwagger2
@Api("sys")
@Slf4j
public class System {

    @Autowired
    MyShiroRealm myShiroRealm;

    @ApiOperation(value = "获取用户", notes = "根据url的id来获取对象", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "登录失败"),
            @ApiResponse(code = 404, message = "用户不存在")
            })
    @PostMapping(value = "/login")
    public ResponseEntity<Result> home(@Valid @RequestBody User reqUser) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(reqUser.getName(), reqUser.getPwd());

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            log.info("对用户[" + reqUser.getName() + "]进行登录验证..验证未通过,未知账户");
            return Result.error(ResultCode.PEOPLE_NOT_FOUND,  NOT_FOUND);
        } catch (IncorrectCredentialsException ice) {
            log.info("对用户[" + reqUser.getName() + "]进行登录验证..验证未通过,错误的凭证");
            return Result.error(ResultCode.PWD_WRONG,  UNAUTHORIZED);
        } catch (LockedAccountException lae) {
            log.info("对用户[" + reqUser.getName() + "]进行登录验证..验证未通过,账户已锁定");
            return Result.error(ResultCode.ACCOUNT_LOCKED,  FORBIDDEN);
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + reqUser.getName() + "]进行登录验证..验证未通过,错误次数过多");
            return Result.error(ResultCode.MANY_WRONG,  FORBIDDEN);
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + reqUser.getName() + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();

            return Result.error(ResultCode.NOT_AUTHORIZED,  UNAUTHORIZED);
        }

        return Result.success();
    }

    @ApiOperation(value = "退出", notes = "退出登录")
    @PostMapping(value = "/logout")
    public ResponseEntity<Result> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return Result.success();
    }

    @ApiOperation(value = "异常", notes = "异常处理")
    @PostMapping(value = "/clearShriroCache")
    public ResponseEntity<Result> clearShiroCache(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        myShiroRealm.clearCache(principals);
        return Result.success();
    }
}