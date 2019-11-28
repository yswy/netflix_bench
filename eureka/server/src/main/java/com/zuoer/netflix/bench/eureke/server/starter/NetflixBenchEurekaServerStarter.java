package com.zuoer.netflix.bench.eureke.server.starter;

import com.zuoer.netflix.bench.base.AbstractNetflixServerBenchStarter;
import com.zuoer.netflix.bench.base.application.NetflixBenchWebApplication;

/**
 * 
 * @author zuoer
 *
 * @version $Id: NetflixBenchApplication.java, v 0.1 2019年11月11日 上午11:41:23 zuoer Exp $
 */

public class NetflixBenchEurekaServerStarter  extends AbstractNetflixServerBenchStarter{

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
	    try {
	    	NetflixBenchWebApplication app = new NetflixBenchWebApplication(NetflixBenchEurekaServerStarter.class);
			app.run(args);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
	}
	
}
