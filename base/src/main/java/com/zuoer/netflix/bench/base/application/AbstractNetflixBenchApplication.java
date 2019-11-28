/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.application;

import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ResourceLoader;

import com.zuoer.netflix.bench.base.spring.bean.name.BenchBeanNameGenerator;

/**
 * 抽象的Bench应用，继承自Spring应用<br>
 * initialize->setup
 * 
 * @author chenbug
 *
 * @version $Id: AbstractBenchApplication.java, v 0.1 2018年2月26日 下午3:40:26 chenbug Exp $
 */
public abstract class AbstractNetflixBenchApplication extends SpringApplication {
	
	/**
	 * 初始化
	 * 
	 * @throws Exception
	 */
	protected void initialize() throws Exception {

	}

	/**
	 * setup前置方法
	 * 
	 * @throws Exception
	 */
	protected void setupBefore() throws Exception {

	}

	protected void setupAfter() throws Exception {

	}

	public AbstractNetflixBenchApplication(Class<?>... primarySources) throws Exception {
		super(primarySources);
		initialize();
		setup();
	}

	public AbstractNetflixBenchApplication(ResourceLoader resourceLoader, Class<?>... primarySources) throws Exception {
		super(resourceLoader, primarySources);
		initialize();
		setup();
	}

	protected void setup() throws Exception {
		this.setBeanNameGenerator(BenchBeanNameGenerator.INSTANCE);
	}

}
