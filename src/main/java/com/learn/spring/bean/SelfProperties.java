package com.learn.spring.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class SelfProperties {
	@Value("${com.version}")
	private String version;

	@Value("${com.description}")
	private String description;
}
