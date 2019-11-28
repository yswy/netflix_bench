package com.zuoer.netflix.bench.base;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 抽象的服务提供者客户端启动
 * 
 * @author zuoer
 *
 * @version $Id: AbstractNetflixClientBenchStarter.java, v 0.1 2019年11月26日 下午2:27:27 zuoer Exp $
 */
@EnableEurekaClient  //开启服务客户端向服务端注册功能 
public abstract class AbstractNetflixClientBenchStarter extends AbstractBenchStarter{

}
