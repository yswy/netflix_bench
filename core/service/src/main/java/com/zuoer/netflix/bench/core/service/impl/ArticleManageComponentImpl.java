/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zuoer.netflix.bench.core.model.request.ArticleCreateRequest;
import com.zuoer.netflix.bench.core.model.request.ArticleModifyRequest;
import com.zuoer.netflix.bench.core.model.result.ArticleOperateResult;
import com.zuoer.netflix.bench.core.service.ArticleManageComponent;
import com.zuoer.netflix.bench.dal.entity.ArticleDO;
import com.zuoer.netflix.bench.dal.entity.TagArticleDO;
import com.zuoer.netflix.bench.dal.entity.TagDO;
import com.zuoer.netflix.bench.dal.mapper.ArticleMapper;
import com.zuoer.netflix.bench.dal.mapper.TagArticleMapper;
import com.zuoer.netflix.bench.dal.mapper.TagMapper;

/**
 * 
 * @author zuoer
 *
 * @version $Id: ArticleManageComponentImpl.java, v 0.1 2019年12月5日 下午6:01:16 zuoer Exp $
 */
@Component
public class ArticleManageComponentImpl implements ArticleManageComponent {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private TagArticleMapper tagArticleMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zuoer.netflix.bench.core.service.ArticleManageComponent#create(com.zuoer.netflix.bench.core.model.request.ArticleCreateRequest)
	 */
	@Override
	public ArticleOperateResult create(ArticleCreateRequest request) {
		// TODO Auto-generated method stub
		ArticleOperateResult result = new ArticleOperateResult();
		result.setSuccess(true);
		ArticleDO article = new ArticleDO();
		article.setTitle(request.getTitle());
		article.setMdMaterial(request.getMdMaterial());
		article.setHtmlMaterial(request.getHtmlMaterial());
		article.setIntroduction(request.getIntroduction());
		articleMapper.insert(article);
		Integer articleId = article.getId();
		// 处理 tags
		List<String> tags = request.getTagNameList();
		for (String item : tags) {

			TagDO tag = tagMapper.selectTagByName(item);
			if (null == tag) {
				tag = new TagDO();
				tag.setName(item);
				tagMapper.insert(tag);
			}
			TagArticleDO tagArticle = new TagArticleDO();
			tagArticle.setTagId(tag.getId());
			tagArticle.setArticleId(articleId);
			tagArticleMapper.insert(tagArticle);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zuoer.netflix.bench.core.service.ArticleManageComponent#modify(com.zuoer.netflix.bench.core.model.request.ArticleModifyRequest)
	 */
	@Override
	public ArticleOperateResult modify(ArticleModifyRequest request) {
		// TODO Auto-generated method stub
		ArticleOperateResult result = new ArticleOperateResult();
		result.setSuccess(true);
		ArticleDO article =articleMapper.selectById(request.getId());
		article.setIntroduction(request.getIntroduction());
	    article.setHtmlMaterial(request.getHtmlMaterial());
	    article.setMdMaterial(request.getMdMaterial());
	    article.setTitle(request.getTitle());
	    articleMapper.updateById(article);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zuoer.netflix.bench.core.service.ArticleManageComponent#deleteByIds(java.util.List)
	 */
	@Override
	public ArticleOperateResult deleteByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		ArticleOperateResult result = new ArticleOperateResult();
		result.setSuccess(true);
		articleMapper.deleteByIds(ids);
		return result;
	}

}
