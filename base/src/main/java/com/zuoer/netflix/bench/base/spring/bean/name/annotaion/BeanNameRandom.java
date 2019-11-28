package com.zuoer.netflix.bench.base.spring.bean.name.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * bean名称随机
 * 
 * @author chenbug
 *
 * @version $Id: BeanNameRandom.java, v 0.1 2018年3月7日 上午11:50:30 chenbug Exp $
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface BeanNameRandom {

}
