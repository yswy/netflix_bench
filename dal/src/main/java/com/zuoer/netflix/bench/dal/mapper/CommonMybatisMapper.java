/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.dal.mapper;

import java.util.List;

/**
 * 
 * @author zuoer
 *
 * @version $Id: AbstractCommonMapper.java, v 0.1 2019年11月28日 下午4:10:52 zuoer Exp $
 */
public interface CommonMybatisMapper<T,PKTYPE> {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T selectById(PKTYPE id);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteById(PKTYPE id);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteByIds(List<PKTYPE> id);
	
	/***
	 * 插入
	 * @param dbDo
	 * @return
	 */
	public PKTYPE insert(T dbDo);
	
	/**
	 * 根据id更新
	 * @param dbDo
	 */
	public void updateById(T dbDo);
	
	/**
	 * 根据id集合查询
	 * @param ids
	 * @return
	 */
	public List<T> selectByIds(List<PKTYPE> ids);
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<T> selectAll();
	
	/**
	 * 批量插入
	 * @param dbDo
	 * @return
	 */
	public PKTYPE insertBatch(List<T> dbDo);
}
