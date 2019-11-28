package com.zuoer.netflix.bench.web.home.form;

/**
 * 修改简历表单
 *
 * @author James
 */
public class ResumeModifyForm {

	/**
	 * 所修改博客的 id
	 */
	private Integer id;

	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * markdown 内容
	 */
	private String mdMaterial;
	/**
	 * markdown 转 html 后的内容
	 */
	private String htmlMaterial;
	/**
	 * 文章内容描述
	 */
	private String description;

	/**
	 * 原始 tags
	 */
	private String rawTags;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRawTags() {
		return rawTags;
	}

	public void setRawTags(String rawTags) {
		this.rawTags = rawTags;
	}

}
