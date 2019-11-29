package com.zuoer.netflix.bench.web.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zuoer.netflix.bench.base.utils.StringUtils;
import com.zuoer.netflix.bench.core.constant.SessionConstants;
import com.zuoer.netflix.bench.core.model.User;
import com.zuoer.netflix.bench.core.request.UserRegisterRequest;
import com.zuoer.netflix.bench.core.result.UserOperateResult;
import com.zuoer.netflix.bench.core.service.UserManageComponent;
import com.zuoer.netflix.bench.core.service.UserQueryComponent;
import com.zuoer.netflix.bench.web.home.controller.base.BaseController;
import com.zuoer.netflix.bench.web.home.form.UserLoginForm;
import com.zuoer.netflix.bench.web.home.form.UserRegisterForm;

/**
 * 用户登录控制器
 *
 * @author James
 */
@Controller
public class UserController extends BaseController {

	@Autowired
	private UserQueryComponent userQueryComponent;

	@Autowired
	private UserManageComponent userManageComponent;

	/**
	 * 前台用户登录 表单提交
	 */
	@PostMapping("/userlogin.f")
	public String fFrontUserLogin(HttpServletRequest request, Model model, @Valid UserLoginForm loginForm, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			model.addAttribute("message", errors.get(0).getDefaultMessage());
			return "userlogin";
		}
		User user = userQueryComponent.getByUserName(loginForm.getUsername());

		if (user == null || !StringUtils.equals(loginForm.getPassword(), user.getPassword())) {
			model.addAttribute("message", "用户名或密码错误");
			return "userlogin";
		}
		request.getSession().setAttribute(SessionConstants.SESSION_CURRENT_USER, user);
		return "redirect:/admin/index";

	}

	/**
	 * 前台用户注册 表单提交
	 */
	@PostMapping("/userregister.f")
	public String fFrontUserRegister(@Valid UserRegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request, Model model, User user) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			return "redirect:/userregister";
		}

		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();

		UserOperateResult userOperateResult = userManageComponent.register(userRegisterRequest);

		if (!userOperateResult.isSuccess()) {
			return "redirect:/userregister";
		}

		// 跳转登录
		return "redirect:/userlogin";
	}

	@GetMapping("/usersignout.c")
	public String cFrontUserSignout(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionConstants.SESSION_CURRENT_USER);
		return "redirect:index";
	}
}
