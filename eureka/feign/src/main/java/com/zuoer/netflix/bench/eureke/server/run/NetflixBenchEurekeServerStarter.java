package com.zuoer.netflix.bench.eureke.server.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.zuoer.netflix.bench.base.application.NetflixBenchWebApplication;

/**
 * 
 * @author zuoer
 *
 * @version $Id: NetflixBenchApplication.java, v 0.1 2019年11月11日 上午11:41:23 zuoer Exp $
 */
@EnableEurekaServer
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, JdbcTemplateAutoConfiguration.class,
		//HttpEncodingAutoConfiguration.class, ServletWebServerFactoryAutoConfiguration.class, 
		SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class,
		//DispatcherServletAutoConfiguration.class,
		RedisReactiveAutoConfiguration.class
		// 不支持devtools
		// LocalDevToolsAutoConfiguration.class,
		// 不支持devtools
		// DevToolsDataSourceAutoConfiguration.class
})
public class NetflixBenchEurekeServerStarter {

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
	    try {
	    	NetflixBenchWebApplication app = new NetflixBenchWebApplication(NetflixBenchEurekeServerStarter.class);
			app.run(args);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
	}
	
}
