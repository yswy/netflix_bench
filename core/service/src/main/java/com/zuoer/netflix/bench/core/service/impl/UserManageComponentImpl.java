/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zuoer.netflix.bench.base.utils.ConverterUtils;
import com.zuoer.netflix.bench.base.utils.StringUtils;
import com.zuoer.netflix.bench.core.model.enums.ErrorCodeEnum;
import com.zuoer.netflix.bench.core.model.request.UserRegisterRequest;
import com.zuoer.netflix.bench.core.model.result.UserOperateResult;
import com.zuoer.netflix.bench.core.service.UserManageComponent;
import com.zuoer.netflix.bench.dal.entity.UserDO;
import com.zuoer.netflix.bench.dal.mapper.UserMapper;

/**
 * 
 * @author zuoer
 *
 * @version $Id: UserManageComponentImpl.java, v 0.1 2019年12月6日 下午3:20:43 zuoer Exp $
 */
@Component
public class UserManageComponentImpl implements UserManageComponent {

	@Autowired
	private UserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.UserManageComponent#register(com.zuoer.netflix.bench.core.model.request.UserRegisterRequest)
	 */
	@Override
	public UserOperateResult register(UserRegisterRequest request) {
		// TODO Auto-generated method stub
		UserOperateResult result=new UserOperateResult();
		result.setSuccess(true);
		UserDO exsitUser=userMapper.selectByUserName(request.getUsername());
		if(exsitUser!=null) {
			result.setSuccess(false);
    		result.setError(ErrorCodeEnum.USER_NAME_EXSITED.errorCode());
    		return result;
		}
		UserDO userDO=new UserDO();
		ConverterUtils.convert(request, userDO);
		userMapper.insert(userDO);
		return result;
	}

}
