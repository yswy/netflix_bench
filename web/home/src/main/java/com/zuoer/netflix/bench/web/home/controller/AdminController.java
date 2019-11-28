package com.zuoer.netflix.bench.web.home.controller;

import static me.zbl.fullstack.consts.ViewConsts.*;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuoer.netflix.bench.core.model.Article;
import com.zuoer.netflix.bench.core.model.Resume;
import com.zuoer.netflix.bench.core.request.ArticleCreateRequest;
import com.zuoer.netflix.bench.core.request.ArticleModifyRequest;
import com.zuoer.netflix.bench.core.result.ArticleOperateResult;
import com.zuoer.netflix.bench.core.service.ArticleManageComponent;
import com.zuoer.netflix.bench.core.service.ArticleQueryComponent;
import com.zuoer.netflix.bench.web.home.controller.base.BaseController;
import com.zuoer.netflix.bench.web.home.form.AdminUserPwdModifyForm;
import com.zuoer.netflix.bench.web.home.form.BlogAddForm;
import com.zuoer.netflix.bench.web.home.form.BlogModifyForm;
import com.zuoer.netflix.bench.web.home.form.UserLoginForm;

import me.zbl.fullstack.entity.dto.request.TableKeyModel;
import me.zbl.fullstack.entity.vo.ResumeModifyModel;
import me.zbl.fullstack.service.api.IAdminUserService;
import me.zbl.fullstack.service.api.IAdminUserService.ModifyPwdResult;
import me.zbl.fullstack.service.api.IResumeService;

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
  IAdminUserService mAdminUserService;
  @Autowired
  IResumeService mResumeService;

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
	  ArticleCreateRequest request=new ArticleCreateRequest();
	  request.setHtmlMaterial(blogAddForm.getHtmlMaterial());
	  request.setIntroduction(blogAddForm.getDescription());
	  request.setMdMaterial(blogAddForm.getMdMaterial());
	  request.setTitle(blogAddForm.getTitle());
	  request.setTagNameList(blogAddForm.getRawTags());
	  ArticleOperateResult articleOperateResult= articleManageComponent.create(request);
	  if(!articleOperateResult.isSuccess()) {
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
	  ArticleModifyRequest request=new ArticleModifyRequest();
	  request.setHtmlMaterial(modifyForm.getHtmlMaterial());
	  request.setIntroduction(modifyForm.getDescription());
	  request.setMdMaterial(modifyForm.getMdMaterial());
	  request.setTitle(modifyForm.getTitle());
	  request.setTagNameList(modifyForm.getRawTags());
	  request.setId(modifyForm.getId());
	  ArticleOperateResult articleOperateResult= articleManageComponent.modify(request);
	  if(!articleOperateResult.isSuccess()) {
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
    return mBlogService.getArticleList();
  }

  /**
   * 批量删除博客
   */
  @DeleteMapping("/blog_delete.j")
  @ResponseBody
  public Object jAdminBlogDelete(@RequestBody TableKeyModel model) {
    mBlogService.blogDelete(model);
    return responseSimpleOK();
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
    return mAdminUserService.getAdminUserJson();
  }

  /**
   * 后台用户批量删除
   */
  @DeleteMapping("/admin_use_delete.j")
  @ResponseBody
  public Object jAdminUserDelete(@RequestBody TableKeyModel model) {
    mAdminUserService.deleteAdminUser(model);
    return responseSimpleOK();
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
    if (result.hasErrors()) {
      return responseSimpleError();
    }
    ModifyPwdResult result1;
    try {
      result1 = mAdminUserService.modifyUserPwd(form, request);
    } catch (Exception e) {
      e.printStackTrace();
      return responseSimpleError();
    }
    if (result1 == ModifyPwdResult.SUCCESS) {
      return responseSimpleOK();
    } else {
      return responseSimpleError();
    }
  }

  @GetMapping("/resume_modify")
  public String pUpdateResume(Model model) throws Exception {
    Resume article = mResumeService.getResume();
    ResumeModifyModel modifyModel = new ResumeModifyModel(article);
    addModelAtt(model, VIEW_ARTICLE, modifyModel);
    return "admin/resume_modify";
  }
}
