/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.model.request;

import java.util.List;

/**
 * 文章创建请求
 * 
 * @author zuoer
 *
 * @version $Id: ArticleCreateRequest.java, v 0.1 2019年11月28日 下午5:57:50 zuoer Exp $
 */
public class ResumeModifyRequest {
	
	/**
	 * 主键id
	 */
	private int id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 文章内容
	 */
	private String mdMaterial;

	/**
	 * html 文章内容
	 */
	private String htmlMaterial;
	
	/**
	 * 标签名字集合
	 */
	private List<String> tagNameList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMdMaterial() {
		return mdMaterial;
	}

	public void setMdMaterial(String mdMaterial) {
		this.mdMaterial = mdMaterial;
	}

	public String getHtmlMaterial() {
		return htmlMaterial;
	}

	public void setHtmlMaterial(String htmlMaterial) {
		this.htmlMaterial = htmlMaterial;
	}

	public List<String> getTagNameList() {
		return tagNameList;
	}

	public void setTagNameList(List<String> tagNameList) {
		this.tagNameList = tagNameList;
	}

}
