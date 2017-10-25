package com.learn.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.learn.spring.Application;
import com.learn.spring.bean.Demo;
import com.learn.spring.bean.SelfProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DemoServiceTest {
	
	@Autowired
	DemoService demoService;
	
	@Autowired
	SelfProperties selfProperties;
	
	@Test
	public void testGetDemo(){
		Demo demo = demoService.getUserById(6);
		Assertions.assertThat(demo.getName()).isEqualTo("Angel");
		Assertions.assertThat(selfProperties.getVersion()).isEqualTo(1);
		Assertions.assertThat(selfProperties.getDescription()).isEqualTo("test");		
	}

}
