package com.zimu.study.netty2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages={"com.zimu.study"})
public class NettyApplication {
	public static void main(final String[] args) {
		SpringApplication.run(NettyApplication.class, args);
	}
}
