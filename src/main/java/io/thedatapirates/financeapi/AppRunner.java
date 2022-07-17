package io.thedatapirates.financeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppRunner {

	/**
	 * The main method
	 *
	 * @param args arguments needed to run application
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);
	}

	/**
	 * Configuration to allow cross-origin requests from the front-end
	 */
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST")
						.allowedOrigins("*");
			}
		};
	}

	/**
	 * Creates a password encoder
	 *
	 * @return instance of BCrypt password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
