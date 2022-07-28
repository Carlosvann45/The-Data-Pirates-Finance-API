package io.thedatapirates.financeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
        .apis(RequestHandlerSelectors.basePackage("io.thedatapirates.financeapi"))
        .build();
  }
}
