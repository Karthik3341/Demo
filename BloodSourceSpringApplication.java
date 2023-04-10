package com.chainsys.bloodsourcespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class )
public class BloodSourceSpringApplication {
	static Logger log=LoggerFactory.getLogger(BloodSourceSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BloodSourceSpringApplication.class, args);
		log.info("Successfully");
	}

}
