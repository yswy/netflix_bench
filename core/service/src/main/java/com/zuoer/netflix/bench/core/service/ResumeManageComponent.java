/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service;

import com.zuoer.netflix.bench.core.request.ResumeModifyRequest;
import com.zuoer.netflix.bench.core.result.ResumeOperateResult;

/**
 *简历管理组件
 * @author zuoer
 *
 * @version $Id: ResumeManageComponent.java, v 0.1 2019年11月28日 下午5:47:02 zuoer Exp $
 */
public interface ResumeManageComponent {
	
	/**
	 * 修改简历
	 * @param request
	 * @return
	 */
	public ResumeOperateResult modify(ResumeModifyRequest request);
}
