/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.dal.mapper;

import com.zuoer.netflix.bench.dal.entity.WebAccessLogDealDO;

/**
 * 
 * @author zuoer
 *
 * @version $Id: WebAccessLogDealMapper.java, v 0.1 2019年11月28日 上午10:08:30 zuoer Exp $
 */
public interface WebAccessLogDealMapper {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public WebAccessLogDealDO selectById(String id);
}
