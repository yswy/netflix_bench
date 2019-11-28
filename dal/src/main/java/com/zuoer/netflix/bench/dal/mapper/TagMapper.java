package com.zuoer.netflix.bench.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zuoer.netflix.bench.dal.entity.ArticleDO;
import com.zuoer.netflix.bench.dal.entity.TagDO;

/**
 * @author James
 */
public interface TagMapper extends CommonMybatisMapper<TagDO,Integer> {

  String COLUMN_LIST = "article.id,article.title,article.introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified";

  /**
   * 根据 tag 的 id 获取文章
   *
   * @param id tag id
   *
   * @return 文章集合
   */
  @Select({
                  "SELECT",
                  COLUMN_LIST,
                  "FROM article,tag,tag_article",
                  "WHERE article.id = tag_article.article_id AND tag.id = tag_article.tag_id",
                  "AND tag.id = #{id}"
          })
  List<ArticleDO> selectArticleByTagId(Integer id);


  @Select({
                  "SELECT id,",
                  "`name` FROM tag WHERE `name` = #{tagName}"
          })
  TagDO selectTagByName(String tagName);
}