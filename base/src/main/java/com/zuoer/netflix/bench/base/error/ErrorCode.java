/**
 * 
 */
package com.zuoer.netflix.bench.base.error;

/**
 * <p>
 * 
 * </p>
 * 
 * @author chenbug
 * @version $Id: ErrorCode.java,v 0.1 2009-8-24 上午11:04:35 chenbug Exp $
 */
public class ErrorCode  {

	private String name;

	private String message;

	public String name() {
		return name;
	}

	public ErrorCode() {
		super();
	}


	public ErrorCode(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.hashCode();
	}


}
