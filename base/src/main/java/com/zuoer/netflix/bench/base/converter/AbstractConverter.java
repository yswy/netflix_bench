/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.converter;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.zuoer.netflix.bench.base.utils.ConverterUtils;

/**
 * 抽象公共转换器
 * @author zuoer
 *
 * @version $Id: AbstractConverter.java, v 0.1 2017年10月16日 下午4:10:10 zuoer Exp $
 */
public class AbstractConverter<FromObj,ToObj> {
	
	/**
	 * 构造一个新的对象
	 * 
	 * @param fromObj
	 * @return
	 */
	protected ToObj newModel() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<ToObj> clasz = (Class<ToObj>) type.getActualTypeArguments()[1];
		try {
			return (ToObj) clasz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("构造类实例异常,clasz=" + clasz, e);
		}
	}
	/**
	 * 子类重载以实现个性化的转换需求
	 * 
	 * @param fromObj
	 * @param toObj
	 */
	protected void convertSpecial(FromObj fromObj, ToObj toObj) {

	}
	

	/**
	 * 转换一个
	 * @param fromObj
	 * @return
	 */
	public ToObj convertOne(FromObj fromObj){
		if(fromObj==null){
		return null;
		}
		ToObj toObj=newModel();
		ConverterUtils.convert(fromObj, toObj);
		convertSpecial(fromObj, toObj);
		return toObj;
	}
	
	/**
	 * 转换多个
	 * 
	 * @param fromObjList
	 * @return
	 */
	public List<ToObj> convertMany(List<FromObj> fromObjList) {
		List<ToObj> returnList = new ArrayList<ToObj>();
		for (FromObj fromObj : fromObjList) {
			returnList.add(this.convertOne(fromObj));
		}
		return returnList;
	}
}
