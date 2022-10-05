package edu.eci.eauction.applicationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.eauction"})
public class EAuctionAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAuctionAPIApplication.class, args);
	}
}
