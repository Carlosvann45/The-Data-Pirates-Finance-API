package theDataPiratesFinanceAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration setup for spring boot web security
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Disables the login page for spring boot and cross-referencing
   * @param security HttpSecurity for configuration
   */
  @Override
  protected void configure(HttpSecurity security) throws Exception {
    security.httpBasic().disable();
    security.csrf().disable();
  }
}
