package com.blog;

import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务
@EnableCaching //redis
@MapperScan("com.blog.mapper")//扫描mapper
public class MyBlogDevApplication {

	public static void main(String[] args) {
		
		//设置时区
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(MyBlogDevApplication.class, args);
	}
}
