package com.learn.spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(0, "请求成功"),
    WARN(-1, "网络异常，请稍后重试"),
    PEOPLE_NOT_FOUND(1001, "用户不存在"),
    PWD_WRONG(1002, "密码错误"),
    PARAMETER_ERROR(2001, "参数错误"),
    ACCOUNT_LOCKED(1003, "账户锁定"),
    MANY_WRONG(1004, "次数过多"),
    NOT_AUTHORIZED(1005, "登录失败")
    ,NOT_LOGIN(1006,"没有登录")
    ,NOT_VIP(1007,"非VIP");

    private int code;
    private String msg;
}
