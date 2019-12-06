/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.utils;

import java.lang.reflect.Field;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.security.util.FieldUtils;

/**
 * 转换集合
 * @author zuoer
 *
 * @version $Id: ConverterUtils.java, v 0.1 2017年10月16日 下午5:26:58 zuoer Exp $
 */
public class ConverterUtils {
	
//	private static final Logger log=Logger.getLogger(ConverterUtils.class);

	public static void convert(Object from,Object to){
		for(Field field:getAllFields(to.getClass())){
			Field fromField;
			try {
				fromField = FieldUtils.getField(from.getClass(), field.getName());
			} catch (IllegalStateException e) {
//				log.info("类型异常,忽略这个属性");
				continue;
			}
			if(fromField!=null && fromField.getType().equals(field.getType())){
				try {
					FieldUtils.setProtectedFieldValue(field.getName(), to,  FieldUtils.getFieldValue(from, fromField.getName()));
				} catch (IllegalArgumentException | IllegalAccessException | IllegalStateException e) {
					// TODO Auto-generated catch block
//					log.info("类型异常,忽略这个属性");
					continue;
				}
			}
		}
	}
	

	public static Field[] getAllFields(Class<?> clasz){
		if(clasz==null){
			return new Field[]{};
		}
		//类本身的属性
		Field[] fieldList=clasz.getDeclaredFields();
				
		//由父类继承下来的属性,递归
		return (Field[]) ArrayUtils.addAll(fieldList, getAllFields(clasz.getSuperclass()));
	}
}
