package io.thedatapirates.financeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Handles adding swagger information and configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Adds swagger configuration for API documentation
     *
     * @return new docket for swagger documentation
     */
    @Bean
    public Docket setSwaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());

    }

    /**
     * Creates API information
     *
     * @return new api info object
     */
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "The Data Pirates Finance API",
                "This API is used as the backend for a Android/Kotlin based frontend mobile application. It stores a user account and has additional functionality to help users manage their finances.",
                "1.0",
                "",
                new springfox.documentation.service.Contact("Carlos Santiago", "https://www.linkedin.com/in/carlos-santiago-b53967224/", "clsantiago@student.fullsail.edu"),
                "",
                "",
                Collections.emptyList());
    }
}

