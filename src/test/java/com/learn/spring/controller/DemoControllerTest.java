package com.learn.spring.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.spring.Application;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration  
public class DemoControllerTest {

	// private MockMvc mvc;
	//
	// @Before
	// public void setUp() throws Exception {
	// mvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
	// }
	//
	// @Test
	// public void getHello() throws Exception {
	// MvcResult mvcResult =
	// mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
	// .andReturn();
	//
	// String expectedResult = "Hello World!";
	// int status = mvcResult.getResponse().getStatus();
	// String content = mvcResult.getResponse().getContentAsString();
	//
	// Assert.assertTrue("错误，正确的返回值为200", status == 200);
	// Assert.assertFalse("错误，正确的返回值为200", status != 200);
	// Assert.assertTrue("数据一致", expectedResult.equals(content));
	// Assert.assertFalse("数据不一致", !expectedResult.equals(content));
	// }

	MockMvc mvc;  
	  
    @Autowired  
    WebApplicationContext webApplicationConnect;  
  
    String expectedJson;  
  
    @Before  
    public void setUp() throws JsonProcessingException {  
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();  
  
    }  
  
    @Test  
    public void testShow() throws Exception {  
        String expectedResult = "HelloWorld!";  
        String uri = "/hello";  
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))  
                .andReturn();  
        int status = mvcResult.getResponse().getStatus();  
        String content = mvcResult.getResponse().getContentAsString();  
  
        Assert.assertTrue("错误，正确的返回值为200", status == 200);  
        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
        System.out.println(content);
        Assert.assertTrue("数据一致", expectedResult.equals(content));  
        Assert.assertFalse("数据不一致", !expectedResult.equals(content));  
    }  

}