/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.model.enums;

import com.zuoer.netflix.bench.base.enums.ErrorEnum;

/**
 * 
 * @author zuoer
 *
 * @version $Id: ErrorCodeEnum.java, v 0.1 2019年12月5日 上午11:14:28 zuoer Exp $
 */
public enum ErrorCodeEnum implements ErrorEnum {
	ADMIN_USER_NOT_FOUND("管理员用户未找到"),
	
	PASSWORD_ERROR("密码错误"),
	
	NEW_PASSWORD_NOT_SAME("新密码不一致"),
	
	USER_NAME_EXSITED("用户名已存在"),
	
	
	;
	
	private String message;

	private ErrorCodeEnum(String message) {
		this.message = message;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public Number value() {
		// TODO Auto-generated method stub
		return null;
	}

}
