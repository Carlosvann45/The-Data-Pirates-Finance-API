package io.thedatapirates.financeapi.data;

import java.util.Objects;

import io.thedatapirates.financeapi.domains.customers.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;

/**
 * This class handles Seeding selected data into the database depending on the application.yml
 */
@Component
public class SeedData implements CommandLineRunner {

  private final Logger logger = LogManager.getLogger(SeedData.class);

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private Environment env;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public static final int DEFAULT_NUMBER_OF_CUSTOMER = 1;

  /**
   * This code gets called when the api started to check if we should load data into
   * the database
   * @param strings string arguments
   */
  @Override
  public void run(String... strings) {
    boolean loadData;

    try {
      // retrieves the value of custom property in application.yml
      loadData = Boolean.parseBoolean(env.getProperty("customers.load"));
    } catch (NumberFormatException ex) {
      logger.error("config variable loadData could not be parsed, falling back to default");
      loadData = true;
    }

    if (loadData) {
      seedDatabase();
    }
  }

  /**
   * Seeds database into the repositories with randomly generated data
   */
  private void seedDatabase() {
    int numberOfCustomers;

    try {
      // retrieves the value of custom property in application.yml
      numberOfCustomers = Integer.parseInt(
          Objects.requireNonNull(env.getProperty("customers.number")));
    } catch (NumberFormatException ex) {
      logger.error("config variable numberOfUsers could not be parsed, falling back to default");
      // If it's not a string, set it to be a default value
      numberOfCustomers = DEFAULT_NUMBER_OF_CUSTOMER;
    }


    Customer customer1 = new Customer("carlosvann45@gmail.com", passwordEncoder.encode("password123"));

    // Persist user to database
    logger.info("Loading " + numberOfCustomers + " customer(s)...");

    customerRepository.save(customer1);

    logger.info("Data load is complete. You can now make request");
  }
}
