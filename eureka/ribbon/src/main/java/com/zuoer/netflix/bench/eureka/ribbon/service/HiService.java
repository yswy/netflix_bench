/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.eureka.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 这是测试用service ，用来调用client提供的服务
 * @author zuoer
 *
 * @version $Id: HiService.java, v 0.1 2019年11月14日 上午10:28:17 zuoer Exp $
 */
@Service
public class HiService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "sayHiError")    //通过 @HystrixCommand中的fallbackMethod指定熔断方法
	public String sayHi(String message) {
		// return restTemplate.getForObject("http://HELLO-SPRING-CLOUD-SERVICE-ADMIN/sayHi?message="+message,String.class);
		// 或者使用yml里面的别名
		return restTemplate.getForObject("http://netflix-bench-eureka-client/hi?message=" + message, String.class);
	}
	
	 /**
     * sayHi方法的熔断方法
     * @param message
     * @return
     */
    public String sayHiError(String message){
        return String.format("goodByewww",message);
    }

}
