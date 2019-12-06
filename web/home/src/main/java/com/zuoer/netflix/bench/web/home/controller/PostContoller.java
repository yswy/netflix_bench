package com.zuoer.netflix.bench.web.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zuoer.netflix.bench.core.model.Article;
import com.zuoer.netflix.bench.core.model.Tag;
import com.zuoer.netflix.bench.core.model.request.ArticleSearchRequest;
import com.zuoer.netflix.bench.core.service.ArticleQueryComponent;
import com.zuoer.netflix.bench.core.service.TagQueryComponent;
import com.zuoer.netflix.bench.web.home.controller.base.BaseController;
import com.zuoer.netflix.bench.web.home.form.ArticleSearchForm;

/**
 * 博客控制器
 *
 * @author James
 */
@Controller
public class PostContoller extends BaseController {

	@Autowired
	private ArticleQueryComponent articleQueryComponent;

	@Autowired
	private TagQueryComponent tagQueryComponent;

	/**
	 * 博客列表页 视图模型： 1.博客列表（文章标题/内容简介/发布时间） 2.标签（tagId/文章总数）
	 */
	@GetMapping("/post")
	public String pPostList(HttpServletRequest request, Model model, Integer tagId) throws Exception {
		List<Article> postViewList = null;
		if (null != tagId) {
			postViewList = articleQueryComponent.getByTagId(tagId);
		} else {
			postViewList = articleQueryComponent.getAll();
		}
		List<Tag> tagViewList = tagQueryComponent.getAll();
		addModelAtt(model, "taglist", tagViewList);
		addModelAtt(model, "postlist", postViewList);
		return "posts";
	}

	/**
	 * 查看文章内容页
	 *
	 * @param id
	 *            文章 id
	 */
	@GetMapping("/blog/{id}")
	public String pFrontBlogView(HttpServletRequest request, Model model, @PathVariable Integer id) throws Exception {
		Article article = articleQueryComponent.getById(id);
		addModelAtt(model, "article", article);
		addModelAtt(model, "title", article.getTitle());
		return "article";
	}

	/**
	 * 文章搜索结果页
	 * <p>
	 * 根据指定条件查找文章
	 * <p>
	 * 视图模型： 博客列表（文章标题/内容简介/发布时间）
	 */
	@GetMapping("/postsearch")
	public String pPostSearch(HttpServletRequest request, Model model, ArticleSearchForm form) throws Exception {

		ArticleSearchRequest articleSearchRequest = new ArticleSearchRequest();
		List<Article> articleList = articleQueryComponent.search(articleSearchRequest);
		addModelAtt(model, "postlist", articleList);
		List<Tag> tagViewList = tagQueryComponent.getAll();
		addModelAtt(model, "taglist", tagViewList);
		return "posts";
	}
}
