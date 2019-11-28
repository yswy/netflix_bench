package com.zuoer.netflix.bench.base.exception;

import com.zuoer.netflix.bench.base.error.ErrorCode;

/**
 * @author chenbug
 * @version $Id: BenchRuntimeException.java,v 0.1 2009-5-21 上午01:15:32 chenbug Exp $
 */
public class BenchRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 75922815058256083L;
	
	protected ErrorCode errorCode;

	/**
	 * 空构造器。
	 */
	public BenchRuntimeException() {
		super();
	}

	public BenchRuntimeException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BenchRuntimeException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BenchRuntimeException(ErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public BenchRuntimeException(ErrorCode errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public <E extends BenchRuntimeException> BenchRuntimeException(E exception) {
		super(exception);
		this.errorCode = exception.getErrorCode();
	}

	public <E extends BenchRuntimeException> BenchRuntimeException(String message, E exception) {
		super(message, exception);
		this.errorCode = exception.getErrorCode();
	}

	/**
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("errorCode=").append(errorCode).append(",").append(super.toString());
		return buf.toString();
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}


}
