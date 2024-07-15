package dev.whyneet.ec_api;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcApiApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EcApiApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
