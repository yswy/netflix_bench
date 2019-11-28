/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service;

import com.zuoer.netflix.bench.core.request.ArticleCreateRequest;
import com.zuoer.netflix.bench.core.request.ArticleModifyRequest;
import com.zuoer.netflix.bench.core.result.ArticleOperateResult;

/**
 * 文章管理组件
 * @author zuoer
 *
 * @version $Id: ArticleManageComponent.java, v 0.1 2019年11月28日 下午5:47:02 zuoer Exp $
 */
public interface ArticleManageComponent {
	
	/**
	 * 创建文章
	 * @param request
	 * @return
	 */
	public ArticleOperateResult create(ArticleCreateRequest request);
	
	/**
	 * 修改文章
	 * @param request
	 * @return
	 */
	public ArticleOperateResult modify(ArticleModifyRequest request);
}
