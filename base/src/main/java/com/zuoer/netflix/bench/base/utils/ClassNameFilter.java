/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.utils;

/**
 * 类名过滤
 * 
 * @author chenbug
 *
 * @version $Id: ClassNameFilter.java, v 0.1 2018年10月12日 上午10:47:40 chenbug Exp $
 */
public interface ClassNameFilter {

	public boolean accept(String className);
}
