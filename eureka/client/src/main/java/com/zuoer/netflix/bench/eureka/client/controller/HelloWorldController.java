/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.eureka.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个controller会作为rest服务被注册到eurekaServer
 * 
 * @author zuoer
 *
 * @version $Id: HelloWorldController.java, v 0.1 2019年11月13日 下午5:12:01 zuoer Exp $
 */
@RestController
public class HelloWorldController {

//	// 注入当前端口号
//	@Value("${eureka.client.port}")
//	private String port;

	@RequestMapping(value = "hiWorld", method = RequestMethod.GET)
	public String sayHi(@RequestParam(value = "message") String message) {
		return String.format("Hi，rest message is : %s i am from port : %s", message, "123456");
	}
}
