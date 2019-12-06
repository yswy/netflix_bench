/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zuoer.netflix.bench.core.model.Resume;
import com.zuoer.netflix.bench.core.service.ResumeQueryComponent;
import com.zuoer.netflix.bench.core.service.converter.ResumeConverter;
import com.zuoer.netflix.bench.dal.mapper.ResumeMapper;

/**
 * 
 * @author zuoer
 *
 * @version $Id: ResumeQueryComponentImpl.java, v 0.1 2019年12月6日 下午3:18:42 zuoer Exp $
 */
@Component
public class ResumeQueryComponentImpl implements ResumeQueryComponent {
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private ResumeConverter resumeConverter;

	/* (non-Javadoc)
	 * @see com.zuoer.netflix.bench.core.service.ResumeQueryComponent#getLastOne()
	 */
	@Override
	public Resume getLastOne() {
		// TODO Auto-generated method stub
		return resumeConverter.convertOne(resumeMapper.selectById(1));
	}

}
