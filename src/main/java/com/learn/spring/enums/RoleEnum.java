package com.learn.spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  RoleEnum implements BaseEnum{
    USER(0, "user"),
    VIP(1, "vip"),
    TEACHER(2, "teacher"),
    EXPERT(3,"expert");

    private int code;
    private String value;
}
