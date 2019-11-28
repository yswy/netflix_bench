/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.eureka.ribbon.autoconfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

/**
 * spring配置
 * 
 * @author chenbug
 *
 * @version $Id: BenchPlatformWebServiceAutoConfig.java, v 0.1 2018年2月23日
 *          下午12:25:24 chenbug Exp $
 */
@Configuration
@ComponentScan({"com.zuoer.netflix.bench.*"})
@ImportResource("classpath*:META-INF/spring/netflix-bench-*.xml")
public class NetflixBenchEurekaRibbonAutoConfig {

	@Bean
    @LoadBalanced //注解表明开启负载均衡功能
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
