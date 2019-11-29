/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service;

import com.zuoer.netflix.bench.core.request.UserRegisterRequest;
import com.zuoer.netflix.bench.core.result.UserOperateResult;

/**
 * 用户管理组件
 * @author zuoer
 *
 * @version $Id: UserManageComponent.java, v 0.1 2019年11月28日 下午5:47:02 zuoer Exp $
 */
public interface UserManageComponent {

	/**
	 * 注册
	 * @param request
	 * @return
	 */
	public UserOperateResult register(UserRegisterRequest request);
}
