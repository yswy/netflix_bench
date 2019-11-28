package com.zuoer.netflix.bench.web.home.form;

/**
 * 后台用户密码修改
 *
 * @author James
 * @date 18-1-25
 */
public class AdminUserPwdModifyForm {

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
