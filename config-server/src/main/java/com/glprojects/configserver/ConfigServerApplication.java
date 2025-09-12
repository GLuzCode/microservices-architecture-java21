package com.glprojects.configserver;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

	@PostConstruct
	public void logSearchLocations() {
		String locations = env.getProperty("spring.cloud.config.server.native.search-locations");
		System.out.println("Config Server buscando en: " + locations);
	}

}
