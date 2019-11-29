/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.request;

import java.util.Date;

/**
 * 用户注册请求
 * 
 * @author zuoer
 *
 * @version $Id: UserRegisterRequest.java, v 0.1 2019年11月28日 下午7:36:00 zuoer Exp $
 */
public class UserRegisterRequest {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 生日
	 */
	private Date birthDate;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * github地址
	 */
	private String github;

	/**
	 * qq
	 */
	private String qq;

	/**
	 * 微信
	 */
	private String wecaht;

	/**
	 * 头像url
	 */
	private String avatar;

	/**
	 * 状态/0:正常/1:封禁
	 */
	private Integer status;

	/**
	 * 等级
	 */
	private Integer level;

	private String hobby;

	private String motto;

	private String mdInfo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWecaht() {
		return wecaht;
	}

	public void setWecaht(String wecaht) {
		this.wecaht = wecaht;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getMdInfo() {
		return mdInfo;
	}

	public void setMdInfo(String mdInfo) {
		this.mdInfo = mdInfo;
	}

}
