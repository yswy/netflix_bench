/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service;

import com.zuoer.netflix.bench.core.model.Article;

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
}
