package theDataPiratesFinanceAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppRunner {

	/**
	 * The main method
	 * @param args arguments needed to run application
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class);
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
}
