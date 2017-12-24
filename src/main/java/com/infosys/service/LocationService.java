package com.infosys.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.infosys")
public class LocationService {

	public static void main(String[] args) {
		SpringApplication.run(LocationService.class, args);
	}
}