/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.model.request;

/**
 * 管理员密码修改请求
 * 
 * @author zuoer
 *
 * @version $Id: AdminUserPasswordModifyRequest.java, v 0.1 2019年11月28日 下午5:57:50 zuoer Exp $
 */
public class AdminUserPasswordModifyRequest {

	/**
	 * 主键id
	 */
	private int id;

	/**
	 * 原密码
	 */
	private String oriPwd;

	/**
	 * 新密码
	 */
	private String newPwd;

	/**
	 * 确认密码
	 */
	private String confirmPwd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriPwd() {
		return oriPwd;
	}

	public void setOriPwd(String oriPwd) {
		this.oriPwd = oriPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

}
