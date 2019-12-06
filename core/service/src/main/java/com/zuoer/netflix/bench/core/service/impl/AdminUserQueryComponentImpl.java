/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.discovery.converters.Auto;
import com.zuoer.netflix.bench.core.model.AdminUser;
import com.zuoer.netflix.bench.core.service.AdminUserQueryComponent;
import com.zuoer.netflix.bench.core.service.converter.AdminUserConverter;
import com.zuoer.netflix.bench.dal.mapper.AdminUserMapper;

/**
 * 
 * @author zuoer
 *
 * @version $Id: AdminUserQueryComponentImpl.java, v 0.1 2019年12月5日 下午12:24:19 zuoer Exp $
 */
@Component
public class AdminUserQueryComponentImpl implements AdminUserQueryComponent {
	
	@Autowired
	private AdminUserMapper adminUserMapper;

	@Autowired
	private AdminUserConverter adminUserConverter;
	
	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.AdminUserQueryComponent#getAll()
	 */
	@Override
	public List<AdminUser> getAll() {
		// TODO Auto-generated method stub
		return adminUserConverter.convertMany(adminUserMapper.selectAll());
	}

	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.AdminUserQueryComponent#getByUserName(java.lang.String)
	 */
	@Override
	public AdminUser getByUserName(String userName) {
		// TODO Auto-generated method stub
		return adminUserConverter.convertOne(adminUserMapper.selectByUserName(userName));
	}

}
