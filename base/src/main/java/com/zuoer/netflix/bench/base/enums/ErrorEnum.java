package com.zuoer.netflix.bench.base.enums;

import com.zuoer.netflix.bench.base.error.ErrorCode;

/**
 * 错误枚举
 * 
 * @author chenbug
 * @version $Id: ErrorEnum.java,v 0.1 2008-12-30 上午10:22:20 chenbug Exp $
 */
public interface ErrorEnum extends EnumBase {

	/**
	 * 是否相等
	 * 
	 * @param errorCode
	 * @return
	 */
	public default boolean equals(ErrorCode errorCode) {
		return this.name().equals(errorCode.getName());
	}

	public default ErrorCode errorCode() {
		return new ErrorCode(name(), message());
	}
}
