package com.wp.myboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@SpringBootApplication // 作用相同 @Configuration @EnableAutoConfiguration @ComponentScan
@EnableScheduling//增加支持定时任务的注解
@EnableTransactionManagement//开启事物
public class MyBootApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MyBootApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
