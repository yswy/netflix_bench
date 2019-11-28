package com.zuoer.netflix.bench.dal.mapper;

import com.zuoer.netflix.bench.dal.entity.ResumeDO;

/**
 * 简历
 *
 * @author James
 */
public interface ResumeMapper extends CommonMybatisMapper<ResumeDO,Integer> {

  String COLUMN_LIST = "resume.id,title,introduction,resume.gmt_create AS gmtCreate,resume.gmt_modified AS gmtModified";
}