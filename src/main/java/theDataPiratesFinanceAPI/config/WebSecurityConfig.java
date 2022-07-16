package theDataPiratesFinanceAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import theDataPiratesFinanceAPI.domains.customers.CustomerServiceImpl;
import theDataPiratesFinanceAPI.utility.JwtFilter;

/**
 * Configuration setup for spring boot web security
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomerServiceImpl customerService;

  @Autowired
  private JwtFilter jwtFilter;

  /**
   * Configures Http Security to utilize custom authorization
   *
   * @param security HttpSecurity for configuration
   * */
  @Override
  protected void configure(HttpSecurity security) throws Exception {
    security.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/customers/authenticate")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }

  /**
   * Configures Authentication manager builder
   *
   * @param auth authentication manager builder to configure
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customerService);
  }

  /**
   * Gets authentication manager
   *
   * @return Authentication manger bean
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
