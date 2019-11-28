package com.zuoer.netflix.bench.eureka.client.starter;

import com.zuoer.netflix.bench.base.AbstractNetflixClientBenchStarter;
import com.zuoer.netflix.bench.base.application.NetflixBenchWebApplication;

/**
 * 服务提供者 客户端
 * @author zuoer
 *
 * @version $Id: NetflixBenchApplication.java, v 0.1 2019年11月11日 上午11:41:23 zuoer Exp $
 */

public class NetflixBenchEurekaClientStarter  extends AbstractNetflixClientBenchStarter{

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
	    try {
	    	NetflixBenchWebApplication app = new NetflixBenchWebApplication(NetflixBenchEurekaClientStarter.class);
			app.run(args);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
	}
	
}
