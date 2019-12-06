/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service;

import java.util.List;

import com.zuoer.netflix.bench.core.model.Article;
import com.zuoer.netflix.bench.core.model.request.ArticleSearchRequest;

/**
 * 文章查询组件
 * @author zuoer
 *
 * @version $Id: ArticleQueryComponent.java, v 0.1 2019年11月28日 下午5:47:02 zuoer Exp $
 */
public interface ArticleQueryComponent {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Article getById(int id);
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<Article> getAll();
	
	/**
	 * 搜索
	 * @return
	 */
	public List<Article> search(ArticleSearchRequest request);
	
	/**
	 * 根据tagId查询
	 * @param tagId
	 * @return
	 */
	public List<Article> getByTagId(int tagId);
}
