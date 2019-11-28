/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.result;

import com.zuoer.netflix.bench.base.error.ErrorCode;

/**
 * 结果基础类
 * 
 * @author zuoer
 *
 * @version $Id: ResultBase.java, v 0.1 2019年11月28日 下午5:55:14 zuoer Exp $
 */
public abstract class ResultBase {

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 错误
	 */
	private ErrorCode error;

	/**
	 * 详细信息
	 */
	private String detailMessage;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorCode getError() {
		return error;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

}
