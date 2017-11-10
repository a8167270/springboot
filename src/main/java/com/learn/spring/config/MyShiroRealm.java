package com.learn.spring.config;

import com.learn.spring.entity.User;
import com.learn.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    // 为当前登陆成功的用户授予权限和角色，已经登陆成功了


    @Override
    public String getName() {
        return "myShiroRealm";
    }


    // 验证当前登录的用户，获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");

        String username = (String) authenticationToken.getPrincipal(); // 获取用户名
        User user = userService.getUserByName(username);
        if(user != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username,user.getPwd(),getName());
            return authcInfo;
        } else {
             throw new UnknownAccountException();//没找到帐号
        }
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

        String username = (String) principalCollection.getPrimaryPrincipal(); //获取用户名
        String role = userService.getRoleByName(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set roles = new HashSet();
        roles.add(role);
        authorizationInfo.setRoles(roles);

//        authorizationInfo.setStringPermissions(userService.getPermissions(username));
        return authorizationInfo;
    }

}
