/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.base.spring.bean.name;

import java.beans.Introspector;
import java.lang.annotation.Annotation;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Controller;

import com.zuoer.netflix.bench.base.spring.bean.name.annotaion.BeanNameRandom;
import com.zuoer.netflix.bench.base.utils.ClassUtils;


/**
 * 
 * @author chenbug
 *
 * @version $Id: BenchBeanNameGenerator.java, v 0.1 2018年3月7日 上午11:41:47 chenbug Exp $
 */
public class BenchBeanNameGenerator extends AnnotationBeanNameGenerator {

	public static final BenchBeanNameGenerator INSTANCE = new BenchBeanNameGenerator();

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		// TODO Auto-generated method stub
		Class<?> clasz = null;
		try {
			clasz = ClassUtils.forName(definition.getBeanClassName());
		} catch (ClassNotFoundException e) {
			return super.buildDefaultBeanName(definition);
		}
		if (clasz == null) {
			return super.buildDefaultBeanName(definition);
		}
		if (clasz.getAnnotation(Controller.class) != null) {
			return super.buildDefaultBeanName(definition);
		}
		Class<?> interfaceClass = ClassUtils.getImplementSuitInterface(clasz);
		if (interfaceClass != null) {
			String shortClassName = ClassUtils.getShortClassName(interfaceClass);
			return Introspector.decapitalize(shortClassName);
		} else {
			return super.buildDefaultBeanName(definition);
		}
	}

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String beanName = this.generateBeanNameInner(definition, registry);
		return beanName;
	}

	public String generateBeanNameInner(BeanDefinition definition, BeanDefinitionRegistry registry) {
		// TODO Auto-generated method stub
		String beanName = super.generateBeanName(definition, registry);
		Class<?> clasz = null;
		try {
			clasz = ClassUtils.forName(definition.getBeanClassName());
		} catch (ClassNotFoundException e) {
			return beanName;
		}
		if (clasz == null) {
			return beanName;
		}

		boolean nameRandom = false;
		for (Annotation annotation : clasz.getAnnotations()) {
			if (annotation.annotationType().getSimpleName().equals(BeanNameRandom.class.getSimpleName())) {
				nameRandom = true;
				break;
			}
		}
		if (nameRandom) {
			return beanName + "_" + RandomStringUtils.randomNumeric(10);
		} else {
			return beanName;
		}
	}

}
