/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.web.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author zuoer
 *
 * @version $Id: HelloWorldConntroller.java, v 0.1 2019年11月12日 上午10:55:35 zuoer Exp $
 */

@Controller
public class HelloWorldController {

	@RequestMapping("hello.htm")
	public String hello() {
		return "hello";
	}
}
