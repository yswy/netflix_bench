package com.zuoer.netflix.bench.run.starter;

import com.zuoer.netflix.bench.base.AbstractBenchStarter;
import com.zuoer.netflix.bench.base.application.NetflixBenchWebApplication;

/**
 * 
 * @author zuoer
 *
 * @version $Id: NetflixBenchApplication.java, v 0.1 2019年11月11日 上午11:41:23 zuoer Exp $
 */
public class NetflixBenchStarter extends AbstractBenchStarter{

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
	    try {
	    	NetflixBenchWebApplication app = new NetflixBenchWebApplication(NetflixBenchStarter.class);
			app.run(args);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
	}
	
}
