///**
// * BenchCode.com Inc.
// * Copyright (c) 2005-2009 All Rights Reserved.
// */
//package com.zuoer.netflix.bench.base.mvc;
//
//import java.io.File;
//
//import javax.servlet.DispatcherType;
//
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
//import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
//import org.springframework.boot.web.server.WebServer;
//import org.springframework.stereotype.Component;
//
//import com.bench.common.enums.CommonErrorCodeEnum;
//import com.bench.common.lang.StringUtils;
//import com.bench.common.lang.exception.BenchRuntimeException;
//import com.bench.runtime.core.BenchRuntime;
//import com.bench.runtime.core.configuration.BenchConfiguration;
//import com.bench.runtime.core.lifecycle.BenchLifeCycle;
//import com.bench.web.mvc.htdocs.filter.HtdocsHeaderFilter;
//
//import io.undertow.servlet.api.DeploymentInfo;
//import io.undertow.servlet.api.FilterInfo;
//
///**
// * 静态资源服务器初始化
// * 
// * @author chenbug
// *
// * @version $Id: HtdocsStaticServerInitializer.java, v 0.1 2018年5月10日 下午7:14:00 chenbug Exp $
// */
//@Component
//public class HtdocsStaticServerInitializer implements BenchLifeCycle, DisposableBean {
//
//	WebServer webServer = null;
//
//	@Override
//	public void initializing(BenchRuntime runtime) throws Exception {
//		// TODO Auto-generated method stub
//		if (BenchConfiguration.getInstance().getRunEnvironment().isDeveloperMachine()
//				&& !StringUtils.isEmpty(BenchConfiguration.getInstance().getWeb().getHtdocsStaticPath())) {
//			UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
//			factory.setPort(BenchConfiguration.getInstance().getWeb().getHtdocsStaticServerPort());
//			File htdocsStaticPath = new File(BenchConfiguration.getInstance().getWeb().getHtdocsStaticPath());
//			if (!htdocsStaticPath.exists()) {
//				throw new BenchRuntimeException(CommonErrorCodeEnum.SYSTEM_CONFIG_ERROR, "不存在的htdocs-static资源目录:" + htdocsStaticPath.getPath());
//			}
//			factory.setDocumentRoot(htdocsStaticPath);
//			factory.getDeploymentInfoCustomizers().add(new UndertowDeploymentInfoCustomizer() {
//				@Override
//				public void customize(DeploymentInfo deploymentInfo) {
//					// TODO Auto-generated method stub
//					deploymentInfo.addFilter(new FilterInfo("addHeader", HtdocsHeaderFilter.class));
//					deploymentInfo.addFilterUrlMapping("addHeader", "/*", DispatcherType.REQUEST);
//				}
//			});
//			webServer = factory.getWebServer();
//			webServer.start();
//		}
//	}
//
//	@Override
//	public void destroy() throws Exception {
//		// TODO Auto-generated method stub
//		if (webServer != null) {
//			webServer.stop();
//		}
//	}
//
//}
