///**
// * BenchCode.com Inc.
// * Copyright (c) 2005-2009 All Rights Reserved.
// */
//package com.zuoer.netflix.bench.base.lifecycle;
//
//import com.bench.common.order.Ordered;
//import com.bench.runtime.core.BenchRuntime;
//
///**
// * bench的生命周期config->load->prepare->initialize->start<br>
// * configed阶段后，spring容器已启动
// * 
// * @author chenbug
// *
// * @version $Id: BenchLifeCycle.java, v 0.1 2018年2月24日 上午10:21:10 chenbug Exp $
// */
//public interface BenchLifeCycle extends Ordered {
//
//	/**
//	 * 系统正在配置，此时可以进行系统调整，如注册spring bean，容器未加载
//	 * 
//	 * @param runtime
//	 */
//	public default void configuring(BenchRuntime runtime) {
//
//	}
//
//	/**
//	 * 系统已配置完毕，容器未加载
//	 * 
//	 * @param runtime
//	 */
//	public default void configured(BenchRuntime runtime) {
//
//	}
//
//	/**
//	 * 系统正在加载，一般是读入缓存，从本地加载配置等<br>
//	 * 
//	 * @param runtime
//	 * @throws Exception
//	 */
//	public default void loading(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 系统加载完成<br>
//	 * 
//	 * @param runtime
//	 * @throws Exception
//	 */
//	public default void loaded(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 准备阶段<br>
//	 * 
//	 * @param runtime
//	 * @throws Exception
//	 */
//	public default void preparing(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 准备后阶段<br>
//	 * 
//	 * @param runtime
//	 * @throws Exception
//	 */
//	public default void prepared(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 初始化阶段
//	 */
//	public default void initializing(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 初始化后阶段
//	 * 
//	 * @param runtime
//	 * @throws Exception
//	 */
//	public default void initialized(BenchRuntime runtime) throws Exception {
//
//	}
//
//	/**
//	 * 正在启动
//	 */
//	public default void starting(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 启动成功
//	 */
//	public default void started(BenchRuntime runtime) throws Exception {
//	};
//
//	/**
//	 * 正在关闭
//	 * 
//	 * @param runtime
//	 */
//	public default void shuttingDown(BenchRuntime runtime) {
//
//	}
//
//	/**
//	 * 关闭
//	 * 
//	 * @param runtime
//	 */
//	public default void shutdown(BenchRuntime runtime) {
//
//	}
//
//	/**
//	 * 失败
//	 * 
//	 * @param runtime
//	 */
//	public default void failed(BenchRuntime runtime) {
//
//	}
//
//	@Override
//	default int order() {
//		// TODO Auto-generated method stub
//		return Ordered.DEFAULT_ORDER;
//	}
//}
