package com.zuoer.netflix.bench.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAspectJAutoProxy
@EnableScheduling
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
// 这个必须放在这里，应用一起动就载入,否则会导致无法在代码中加载属性，因为spring不是一开始就扫描全部的属性源
//@PropertySource(value = "classpath:bench-application.properties")
public abstract class AbstractBenchStarter {

	// 确保在应用启动前就初始化
	static {
	}

	public AbstractBenchStarter() {
		// 设置文件编码格式
		System.setProperty("file.encoding", "UTF-8");
	}

	/**
	 * 最小化
	 */
	protected static void minimize() {
	}

}
