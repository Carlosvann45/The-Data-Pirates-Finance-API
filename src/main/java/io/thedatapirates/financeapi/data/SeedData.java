package io.thedatapirates.financeapi.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyRepository;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevel;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelRepository;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
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
    private FrequencyRepository frequencyRepository;

    @Autowired
    private PriorityLevelRepository priorityLevelRepository;

    @Autowired
    private Environment env;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static final int DEFAULT_NUMBER_OF_CUSTOMER = 1;

    /**
     * This code gets called when the api started to check if we should load data into
     * the database
     *
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
            logger.error("config variable numberOfCustomerss could not be parsed, falling back to default");
            // If it's not a string, set it to be a default value
            numberOfCustomers = DEFAULT_NUMBER_OF_CUSTOMER;
        }

        // Persist user to database
        logger.info("Loading " + numberOfCustomers + " customer(s)...");

        Customer customer1 = new Customer("carlosvann45@gmail.com", passwordEncoder.encode("password123"));

        customer1.setDateCreated(new Date(System.currentTimeMillis()));
        customer1.setDateUpdated(new Date(System.currentTimeMillis()));

        try {
            if (customerRepository.findCustomerByUsername(customer1.getUsername()) == null) {
                customerRepository.save(customer1);
            }

            logger.info("Loading frequencies...");

            List<Frequency> frequencyList = new ArrayList<>();

            if (frequencyRepository.findFrequencyById(1L) == null) {
                frequencyList.add(new Frequency(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Daily"));
            }

            if (frequencyRepository.findFrequencyById(2L) == null) {
                frequencyList.add(new Frequency(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Weekly"));
            }

            if (frequencyRepository.findFrequencyById(3L) == null) {
                frequencyList.add(new Frequency(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Monthly"));
            }

            if (frequencyRepository.findFrequencyById(4L) == null) {
                frequencyList.add(new Frequency(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Yearly"));
            }

            frequencyRepository.saveAll(frequencyList);

            logger.info("Loading priority levels...");

            List<PriorityLevel> priorityLevelList = new ArrayList<>();

            if (priorityLevelRepository.findPriorityLevelById(1L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Low",
                        "Lowest level of priority. Its not really at the top of your TODO list."));
            }

            if (priorityLevelRepository.findPriorityLevelById(2L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Medium",
                        "Second lowest level of priority. Its important enough that it's in the middle of your TODO list."));
            }

            if (priorityLevelRepository.findPriorityLevelById(3L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "High",
                        "High level of priority. Its important and its towards the top of your TODO list."));
            }

            if (priorityLevelRepository.findPriorityLevelById(4L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        "Very High",
                        "Top level of priority. Its very important and at the top of your TODO list."));
            }

            priorityLevelRepository.saveAll(priorityLevelList);

        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        logger.info("Data load is complete. You can now make request.");
    }
}
