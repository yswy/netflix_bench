package com.zuoer.netflix.bench.web.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuoer.netflix.bench.base.error.ErrorCode;
import com.zuoer.netflix.bench.base.json.JsonOutputResult;
import com.zuoer.netflix.bench.core.constant.SessionConstants;
import com.zuoer.netflix.bench.core.model.AdminUser;
import com.zuoer.netflix.bench.core.model.Article;
import com.zuoer.netflix.bench.core.model.Resume;
import com.zuoer.netflix.bench.core.model.request.AdminUserPasswordModifyRequest;
import com.zuoer.netflix.bench.core.model.request.ArticleCreateRequest;
import com.zuoer.netflix.bench.core.model.request.ArticleModifyRequest;
import com.zuoer.netflix.bench.core.model.result.AdminUserOperateResult;
import com.zuoer.netflix.bench.core.model.result.ArticleOperateResult;
import com.zuoer.netflix.bench.core.service.AdminUserManageComponent;
import com.zuoer.netflix.bench.core.service.AdminUserQueryComponent;
import com.zuoer.netflix.bench.core.service.ArticleManageComponent;
import com.zuoer.netflix.bench.core.service.ArticleQueryComponent;
import com.zuoer.netflix.bench.core.service.ResumeQueryComponent;
import com.zuoer.netflix.bench.web.home.controller.base.BaseController;
import com.zuoer.netflix.bench.web.home.form.AdminUserPwdModifyForm;
import com.zuoer.netflix.bench.web.home.form.BlogAddForm;
import com.zuoer.netflix.bench.web.home.form.BlogModifyForm;
import com.zuoer.netflix.bench.web.home.form.UserLoginForm;

/**
 * 后台控制器
 *
 * @author James
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private ArticleQueryComponent articleQueryComponent;

	@Autowired
	private ArticleManageComponent articleManageComponent;
	@Autowired
	private AdminUserQueryComponent adminUserQueryComponent;
	@Autowired
	private AdminUserManageComponent adminUserManageComponent;
	@Autowired
	private ResumeQueryComponent resumeQueryComponent;

	/**
	 * 后台首页
	 */
	@GetMapping("")
	public String pAdminIndex(HttpServletRequest request, Model model) {
		return "admin/blog_manage";
	}

	/**
	 * 后台首页
	 */
	@GetMapping("/index")
	public String pAdminIndex2(HttpServletRequest request, Model model) {
		return "admin/blog_manage";
	}

	/**
	 * 后台用户登录页面
	 */
	@GetMapping("/login")
	public String pAdminLogin(HttpServletRequest request, Model model) {
		return "admin/userlogin";
	}

	/**
	 * 写博客页面
	 */
	@GetMapping("/blogadd")
	public String pAdminBlogAdd(HttpServletRequest request, Model model) {
		return "admin/blogadd";
	}

	/**
	 * 编辑博客页面
	 */
	@GetMapping("/blogmodify/{id}")
	public String pAdminBlogModify(HttpServletRequest request, Model model, @PathVariable Integer id) throws Exception {
		Article article = articleQueryComponent.getById(id);
		model.addAttribute("article", article);
		return "admin/blog_modify";
	}

	/**
	 * 博客管理页面
	 */
	@GetMapping("/blogmanage")
	public String pAdminBlogManage(HttpServletRequest request, Model model) {
		return "admin/blog_manage";
	}

	/**
	 * 后台用户登录验证
	 */
	@PostMapping("/login.f")
	public String fAdminLogin(UserLoginForm userLoginForm) {
		return "redirect:/admin/blog_manage";
	}

	/**
	 * 发布文章
	 */
	@PostMapping("/blogadd.f")
	public String fAdminBlogAdd(BlogAddForm blogAddForm) {
		// TODO: 17-12-4 返回 json ，成功则重定向到博客列表
		ArticleCreateRequest request = new ArticleCreateRequest();
		request.setHtmlMaterial(blogAddForm.getHtmlMaterial());
		request.setIntroduction(blogAddForm.getDescription());
		request.setMdMaterial(blogAddForm.getMdMaterial());
		request.setTitle(blogAddForm.getTitle());
		request.setTagNameList(blogAddForm.getRawTags());
		ArticleOperateResult articleOperateResult = articleManageComponent.create(request);
		if (!articleOperateResult.isSuccess()) {
			return "redirect:/error";
		}
		return "redirect:/admin/index";
	}

	/**
	 * 修改文章
	 */
	@PostMapping("blog_modify.f")
	public String fAdminBlogModify(BlogModifyForm modifyForm) {
		// TODO: 17-12-4 返回 json ，成功则重定向到博客列表
		ArticleModifyRequest request = new ArticleModifyRequest();
		request.setHtmlMaterial(modifyForm.getHtmlMaterial());
		request.setIntroduction(modifyForm.getDescription());
		request.setMdMaterial(modifyForm.getMdMaterial());
		request.setTitle(modifyForm.getTitle());
		request.setTagNameList(modifyForm.getRawTags());
		request.setId(modifyForm.getId());
		ArticleOperateResult articleOperateResult = articleManageComponent.modify(request);
		if (!articleOperateResult.isSuccess()) {
			return "redirect:/error";
		}
		return "redirect:/admin/index";
	}

	/**
	 * 获取博客详情列表
	 */
	@GetMapping("/blog_list.j")
	@ResponseBody
	public Object jAdminBlogList() {
		return articleQueryComponent.getAll();
	}

	/**
	 * 批量删除博客
	 */
	@DeleteMapping("/blog_delete.j")
	@ResponseBody
	public Object jAdminBlogDelete(List<Integer> ids) {
		JsonOutputResult jsonResult = new JsonOutputResult();
		ArticleOperateResult articleOperateResult = articleManageComponent.deleteByIds(ids);
		jsonResult.setSuccess(articleOperateResult.isSuccess());
		jsonResult.setError(articleOperateResult.getError());
		jsonResult.setDetailMessage(articleOperateResult.getDetailMessage());
		return jsonResult;
	}

	/**
	 * 后台用户管理页面
	 */
	@GetMapping("/admin_user_manage")
	public String pAdminUserManage() {
		return "admin/admin_user_manage";
	}

	/**
	 * 后台用户管理 json
	 */
	@GetMapping("/admin_user.j")
	@ResponseBody
	public Object jAdminUserList() {
		return adminUserQueryComponent.getAll();
	}

	/**
	 * 后台用户批量删除
	 */
	@DeleteMapping("/admin_use_delete.j")
	@ResponseBody
	public Object jAdminUserDelete(List<Integer> ids) {
		JsonOutputResult jsonResult = new JsonOutputResult();
		AdminUserOperateResult adminUserOperateResult = adminUserManageComponent.deleteByIds(ids);
		jsonResult.setSuccess(adminUserOperateResult.isSuccess());
		jsonResult.setError(adminUserOperateResult.getError());
		jsonResult.setDetailMessage(adminUserOperateResult.getDetailMessage());
		return jsonResult;
	}

	/**
	 * 后台用户密码修改页面
	 */
	@GetMapping("/admin_user_pwd_modify")
	public String pAdminUserPwdModify() {
		return "admin/admin_user_pwd_modify";
	}

	/**
	 * 后台用户密码修改
	 */
	@PostMapping("/admin_user_pwd_modify.f")
	@ResponseBody
	public Object fAdminUserPwdModify(@Valid AdminUserPwdModifyForm form, BindingResult result, HttpServletRequest request) {
		JsonOutputResult jsonResult = new JsonOutputResult();
		if (result.hasErrors()) {
			jsonResult.setSuccess(false);
			jsonResult.setError(new ErrorCode(result.getAllErrors().get(0).getCode(), result.getAllErrors().get(0).getDefaultMessage()));
			return jsonResult;
		}
		AdminUser user = (AdminUser) request.getSession().getAttribute(SessionConstants.SESSION_ADMIN_CURRENT_USER);
		AdminUserPasswordModifyRequest adminUserPasswordModifyRequest=new AdminUserPasswordModifyRequest();
		adminUserPasswordModifyRequest.setConfirmPwd(form.getConfirmPwd());
		adminUserPasswordModifyRequest.setId(user.getId());
		adminUserPasswordModifyRequest.setNewPwd(form.getNewPwd());
		adminUserPasswordModifyRequest.setOriPwd(form.getOriPwd());
		AdminUserOperateResult adminUserOperateResult =adminUserManageComponent.modifyPassword(adminUserPasswordModifyRequest);
		jsonResult.setSuccess(adminUserOperateResult.isSuccess());
		jsonResult.setError(adminUserOperateResult.getError());
		jsonResult.setDetailMessage(adminUserOperateResult.getDetailMessage());
		return jsonResult;
	}

	@GetMapping("/resume_modify")
	public String pUpdateResume(Model model) throws Exception {
		Resume resume = resumeQueryComponent.getLastOne();
		model.addAttribute("resume", resume);
		return "admin/resume_modify";
	}
}
