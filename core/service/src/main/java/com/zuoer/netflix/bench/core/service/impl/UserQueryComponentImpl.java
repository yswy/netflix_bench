/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.discovery.converters.Auto;
import com.zuoer.netflix.bench.core.model.User;
import com.zuoer.netflix.bench.core.service.UserQueryComponent;
import com.zuoer.netflix.bench.core.service.converter.UserConverter;
import com.zuoer.netflix.bench.dal.mapper.UserMapper;

/**
 * 
 * @author zuoer
 *
 * @version $Id: UserQueryComponentImpl.java, v 0.1 2019年12月6日 下午3:21:01 zuoer Exp $
 */
@Component
public class UserQueryComponentImpl implements UserQueryComponent {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserConverter userConverter;

	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.UserQueryComponent#getAll()
	 */
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userConverter.convertMany(userMapper.selectAll());
	}

	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.UserQueryComponent#getByUserName(java.lang.String)
	 */
	@Override
	public User getByUserName(String userName) {
		// TODO Auto-generated method stub
		return userConverter.convertOne(userMapper.selectByUserName(userName));
	}

}
