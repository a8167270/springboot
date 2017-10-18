package com.learn.spring.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.bean.Demo;
import com.learn.spring.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	@Resource
	private DemoService demoService;

	@RequestMapping("/getDemo")
	public Demo getDemo() {
		Demo demo = new Demo();
		demo.setId(1);
		demo.setName("deom");
		return demo;
	}

	@RequestMapping("/zeroException")
	public int zeroException() {
		return 100 / 0;
	}

	@RequestMapping("/save")
	public String save() {
		Demo d = new Demo();

		d.setName("Angel Test");

		demoService.save(d);// 保存数据.
		return "ok.Demo2Controller.save test";
	}
	
	 //地址：http://127.0.0.1:8080/demo2/getById?id=1
    @RequestMapping("/getById")
    public Demo getById(long id){
       return demoService.getById(id);
    }
}
