package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.github.thecoolerSuptelov.*")

public class SpringDataRestApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestApiApplication.class, args);
	}

}
