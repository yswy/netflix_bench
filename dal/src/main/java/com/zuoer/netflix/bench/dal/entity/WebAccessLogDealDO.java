/*
 * Bench Inc.
 * Copyright (c) 2004 All Rights Reserved.
 */
package com.zuoer.netflix.bench.dal.entity;

// auto generated imports
import java.util.Date;

/**
 * A data object class directly models database table <tt>WEB_ACCESS_LOG_DEAL</tt>.<br>
 * web访问日志处理
 *
 *
 * !!!!!!!该文件自动生成，不要修改，否则会被覆盖!!!!!!!
 *
 * @author chenbug
 */
public class WebAccessLogDealDO  {
	// ========== properties ==========

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 是否已处理
	 */
	private boolean deal;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	/**
	 * 访问时间
	 */
	private Date gmtAccess;

	// ========== getters and setters ==========

	/**
	 * Getter method for property <tt>id</tt>.
	 *
	 * @return property value of id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter method for property <tt>id</tt>.
	 *
	 * @param id
	 *            value to be assigned to property id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter method for property <tt>deal</tt>.
	 *
	 * @return property value of deal
	 */
	public boolean isDeal() {
		return deal;
	}

	/**
	 * Setter method for property <tt>deal</tt>.
	 *
	 * @param deal
	 *            value to be assigned to property deal
	 */
	public void setDeal(boolean deal) {
		this.deal = deal;
	}

	/**
	 * Getter method for property <tt>gmtCreate</tt>.
	 *
	 * @return property value of gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * Setter method for property <tt>gmtCreate</tt>.
	 *
	 * @param gmtCreate
	 *            value to be assigned to property gmtCreate
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * Getter method for property <tt>gmtModified</tt>.
	 *
	 * @return property value of gmtModified
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * Setter method for property <tt>gmtModified</tt>.
	 *
	 * @param gmtModified
	 *            value to be assigned to property gmtModified
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * Getter method for property <tt>gmtAccess</tt>.
	 *
	 * @return property value of gmtAccess
	 */
	public Date getGmtAccess() {
		return gmtAccess;
	}

	/**
	 * Setter method for property <tt>gmtAccess</tt>.
	 *
	 * @param gmtAccess
	 *            value to be assigned to property gmtAccess
	 */
	public void setGmtAccess(Date gmtAccess) {
		this.gmtAccess = gmtAccess;
	}
}
