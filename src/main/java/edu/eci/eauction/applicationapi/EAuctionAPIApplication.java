package edu.eci.eauction.applicationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("edu.eci.eauction")
@EnableMongoRepositories("edu.eci.eauction.repositories")
public class EAuctionAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAuctionAPIApplication.class, args);
	}
}
