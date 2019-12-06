/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.zuoer.netflix.bench.core.service.converter;

import org.springframework.stereotype.Component;

import com.zuoer.netflix.bench.base.converter.AbstractConverter;
import com.zuoer.netflix.bench.core.model.User;
import com.zuoer.netflix.bench.dal.entity.UserDO;

/**
 * 
 * 
 * @author zuoer
 *
 * @version $Id: UserConverter.java, v 0.1 2019年12月5日 下午12:33:18 zuoer Exp $
 */
@Component
public class UserConverter extends AbstractConverter<UserDO, User> {

}
