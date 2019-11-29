/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service;

import java.util.List;

import com.zuoer.netflix.bench.core.model.User;

/**
 * 用户查询组件
 * @author zuoer
 *
 * @version $Id: UserQueryComponent.java, v 0.1 2019年11月28日 下午5:47:02 zuoer Exp $
 */
public interface UserQueryComponent {

	/**
	 * 查询全部
	 * @return
	 */
	public List<User> getAll();
	
	/**
	 * 查询全部
	 * @return
	 */
	public User getByUserName(String userName);
}
