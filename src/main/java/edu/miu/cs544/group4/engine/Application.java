package edu.miu.cs544.group4.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication(
		scanBasePackages = "edu.miu.common, edu.miu.common.config, edu.miu.cs.cs544, edu.miu.cs544.group4.engine")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}