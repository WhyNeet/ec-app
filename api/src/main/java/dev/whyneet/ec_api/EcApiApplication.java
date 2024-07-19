package dev.whyneet.ec_api;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ConfigurationPropertiesScan
public class EcApiApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/api");

		SpringApplication app = new SpringApplication(EcApiApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
