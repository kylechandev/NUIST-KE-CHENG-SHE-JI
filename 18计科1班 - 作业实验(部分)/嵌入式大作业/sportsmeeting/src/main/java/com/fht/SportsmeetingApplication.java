package com.fht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableTransactionManagement
//@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.fht.dao")
public class SportsmeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsmeetingApplication.class, args);
	}

}
