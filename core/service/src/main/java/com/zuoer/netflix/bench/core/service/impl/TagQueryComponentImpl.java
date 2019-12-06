/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.discovery.converters.Auto;
import com.zuoer.netflix.bench.core.model.Tag;
import com.zuoer.netflix.bench.core.service.TagQueryComponent;
import com.zuoer.netflix.bench.core.service.converter.TagConverter;
import com.zuoer.netflix.bench.dal.mapper.TagMapper;

/**
 * 
 * @author zuoer
 *
 * @version $Id: TagQueryComponentImpl.java, v 0.1 2019年12月6日 下午3:19:41 zuoer Exp $
 */
@Component
public class TagQueryComponentImpl implements TagQueryComponent {
	
	@Autowired
	private TagMapper tagMapper;
	
	@Autowired
	private TagConverter tagConverter;

	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.TagQueryComponent#getAll()
	 */
	@Override
	public List<Tag> getAll() {
		// TODO Auto-generated method stub
		return tagConverter.convertMany(tagMapper.selectAll());
	}

}
