package com.zuoer.netflix.bench.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zuoer.netflix.bench.dal.entity.ArticleDO;

/**
 * @author James
 */
public interface ArticleMapper extends CommonMybatisMapper<ArticleDO,Integer> {

  String COLUMN_LIST = "article.id,title,introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified";

  @Select({
                  "SELECT",
                  COLUMN_LIST,
                  "FROM",
                  "article",
                  "ORDER BY article.gmt_create DESC"
          })
  List<ArticleDO> getPostViewAllArticles();

  /**
   * 通过 tag id 查找文章
   *
   * @param id tag id
   *
   * @return 符合条件的文章
   */
  @Select({
                  "SELECT",
                  COLUMN_LIST,
                  "FROM article",
                  "INNER JOIN tag_article",
                  "ON tag_article.article_id = article.id",
                  "AND tag_article.tag_id=#{id}",
                  "ORDER BY article.gmt_create DESC"
          })
  List<ArticleDO> getArticleListByTagId(Integer id);

  /**
   * 通过条件查找文章
   *
   * @param form 条件表单
   *
   * @return 符合条件的文章
   */
//  @SelectProvider(type = ArticleSqlProvider.class, method = "getArticleByCondition")
//  List<Article> getArticleListByCondition(ArticleSearchForm form);
}