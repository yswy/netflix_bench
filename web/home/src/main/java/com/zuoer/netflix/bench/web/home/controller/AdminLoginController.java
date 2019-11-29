package com.zuoer.netflix.bench.web.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zuoer.netflix.bench.base.utils.StringUtils;
import com.zuoer.netflix.bench.core.constant.SessionConstants;
import com.zuoer.netflix.bench.core.model.AdminUser;
import com.zuoer.netflix.bench.core.service.AdminUserQueryComponent;
import com.zuoer.netflix.bench.web.home.controller.base.BaseController;
import com.zuoer.netflix.bench.web.home.form.UserLoginForm;

/**
 * 后台登录控制器
 *
 * @author James
 */
@Controller
public class AdminLoginController extends BaseController {

	@Autowired
	private AdminUserQueryComponent adminUserQueryComponent;

	/**
	 * 后台用户登录页面
	 */
	@GetMapping("/adminlogin")
	public String pAdminLogin() {
		return "admin/userlogin";
	}

	/**
	 * 后台用户登录页面
	 */
	@GetMapping("/adminlogin/index")
	public String pAdminLoginIndex() {
		return "admin/userlogin";
	}

	/**
	 * 后台用户登录验证
	 */
	@PostMapping("/adminlogin/login.f")
	public String fAdminLogin(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			return "redirect:/adminlogin?msg=" + errors.get(0).getDefaultMessage();
		}
		AdminUser user = adminUserQueryComponent.getByUserName(userLoginForm.getUsername());
		if (user == null || !StringUtils.equals(userLoginForm.getPassword(), user.getPassword())) {
			return "redirect:/adminlogin?msg=登录失败";
		}
		request.getSession().setAttribute(SessionConstants.SESSION_ADMIN_CURRENT_USER, user);
		return "redirect:/admin/index";
	}
}
