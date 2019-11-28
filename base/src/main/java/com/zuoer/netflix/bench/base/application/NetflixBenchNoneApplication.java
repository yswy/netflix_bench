/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.application;

import org.springframework.boot.WebApplicationType;
import org.springframework.core.io.ResourceLoader;

/**
 * Bench的WEB应用，继承自Spring应用
 * 
 * @author chenbug
 *
 * @version $Id: BenchWebApplication.java, v 0.1 2018年2月26日 下午3:40:26 chenbug
 *          Exp $
 */
public class NetflixBenchNoneApplication extends AbstractNetflixBenchApplication {

	public NetflixBenchNoneApplication(Class<?>... primarySources) throws Exception {
		super(primarySources);
		// TODO Auto-generated constructor stub
	}

	public NetflixBenchNoneApplication(ResourceLoader resourceLoader, Class<?>... primarySources) throws Exception {
		super(resourceLoader, primarySources);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setup() throws Exception {
		// TODO Auto-generated method stub
		super.setup();
		this.setWebApplicationType(WebApplicationType.NONE);
	}

}
