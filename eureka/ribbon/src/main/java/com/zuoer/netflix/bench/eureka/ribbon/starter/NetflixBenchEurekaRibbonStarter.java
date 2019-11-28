package com.zuoer.netflix.bench.eureka.ribbon.starter;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.zuoer.netflix.bench.base.AbstractBenchStarter;
import com.zuoer.netflix.bench.base.application.NetflixBenchWebApplication;

/**
 *通过 @EnableDiscoveryClient 注解开启注册到服务中心
 *@EnableDiscoveryClient 代表的是服务消费者注册到服务注册中心
 *  此注解一般情况下是服务消费者使用

 * @author zuoer
 *
 * @version $Id: NetflixBenchApplication.java, v 0.1 2019年11月11日 上午11:41:23 zuoer Exp $
 */
@EnableDiscoveryClient 
@EnableHystrix      //@EnableHystrix  开启熔断器 
public class NetflixBenchEurekaRibbonStarter extends AbstractBenchStarter{

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
	    try {
	    	NetflixBenchWebApplication app = new NetflixBenchWebApplication(NetflixBenchEurekaRibbonStarter.class);
			app.run(args);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
	}
	
}
