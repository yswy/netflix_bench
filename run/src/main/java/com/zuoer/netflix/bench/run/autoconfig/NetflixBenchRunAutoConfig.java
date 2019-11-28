/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.run.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

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
public class NetflixBenchRunAutoConfig {

}
