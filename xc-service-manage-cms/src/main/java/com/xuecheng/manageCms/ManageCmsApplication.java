package com.xuecheng.manageCms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//基于Spring Boot工程开发
//本项目的根路径下面
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")//扫描指定包下的实体类
@ComponentScan(basePackages = { "com.xuecheng.api" })//扫描接口
@ComponentScan(basePackages = { "com.xuecheng.manageCms" })//扫描接口
@ComponentScan(basePackages = { "com.xuecheng.framework" })//Common包等
public class ManageCmsApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ManageCmsApplication.class, args);
	}

	//远程请求接口调用配置
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}
}


