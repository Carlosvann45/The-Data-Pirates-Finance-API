package io.thedatapirates.financeapi.data;

import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * This code gets called when the api started to check if we should load data into the database
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
        LocalDateTime currentDate = new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // Persist user to database
        logger.info("Loading " + 5 + " customer(s)...");

        Customer customer1 = new Customer("Carlos", "Santiago", "carlosvann45@gmail.com",
                passwordEncoder.encode("password123"));

        Customer customer2 = new Customer("Lauren", "Cotto", "lauren.cotto@gmail.com",
                passwordEncoder.encode("Tomlikeschips"));

        Customer customer3 = new Customer("Aric", "Hylton", "Arich212@gmail.com",
                passwordEncoder.encode("Golfpro-5"));

        Customer customer4 = new Customer("Jeremy", "Dickerson", "jtpilot20@gmail.com",
                passwordEncoder.encode("fu27FU@&"));

        Customer customer5 = new Customer("John", "Selders", "jjselders@student.fullsail.edu",
                passwordEncoder.encode("password123"));

        customer1.setDateCreated(currentDate);
        customer1.setDateUpdated(currentDate);
        customer2.setDateCreated(currentDate);
        customer2.setDateUpdated(currentDate);
        customer3.setDateCreated(currentDate);
        customer3.setDateUpdated(currentDate);
        customer4.setDateCreated(currentDate);
        customer4.setDateUpdated(currentDate);
        customer5.setDateCreated(currentDate);
        customer5.setDateUpdated(currentDate);

        try {
            if (customerRepository.findCustomerByUsername(customer1.getUsername()) == null) {
                customerRepository.save(customer1);
            }

            if (customerRepository.findCustomerByUsername(customer2.getUsername()) == null) {
                customerRepository.save(customer2);
            }

            if (customerRepository.findCustomerByUsername(customer3.getUsername()) == null) {
                customerRepository.save(customer3);
            }

            if (customerRepository.findCustomerByUsername(customer4.getUsername()) == null) {
                customerRepository.save(customer4);
            }

            if (customerRepository.findCustomerByUsername(customer5.getUsername()) == null) {
                customerRepository.save(customer5);
            }

            logger.info("Loading frequencies...");

            List<Frequency> frequencyList = new ArrayList<>();

            if (frequencyRepository.findFrequencyById(1L) == null) {
                frequencyList.add(new Frequency(
                        currentDate,
                        currentDate,
                        "Daily"));
            }

            if (frequencyRepository.findFrequencyById(2L) == null) {
                frequencyList.add(new Frequency(
                        currentDate,
                        currentDate,
                        "Weekly"));
            }

            if (frequencyRepository.findFrequencyById(3L) == null) {
                frequencyList.add(new Frequency(
                        currentDate,
                        currentDate,
                        "Monthly"));
            }

            if (frequencyRepository.findFrequencyById(4L) == null) {
                frequencyList.add(new Frequency(
                        currentDate,
                        currentDate,
                        "Yearly"));
            }

            if (frequencyRepository.findFrequencyById(5L) == null) {
                frequencyList.add(new Frequency(
                        currentDate,
                        currentDate,
                        "Biweekly"));
            }

            frequencyRepository.saveAll(frequencyList);

            logger.info("Loading priority levels...");

            List<PriorityLevel> priorityLevelList = new ArrayList<>();

            if (priorityLevelRepository.findPriorityLevelById(1L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        currentDate,
                        currentDate,
                        "Low",
                        "Lowest level of priority. Its not really at the top of your TODO list."));
            }

            if (priorityLevelRepository.findPriorityLevelById(2L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        currentDate,
                        currentDate,
                        "Medium",
                        "Second lowest level of priority. Its important enough that it's in the middle of your TODO list."));
            }

            if (priorityLevelRepository.findPriorityLevelById(3L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        currentDate,
                        currentDate,
                        "High",
                        "High level of priority. Its important and its towards the top of your TODO list."));
            }

            if (priorityLevelRepository.findPriorityLevelById(4L) == null) {
                priorityLevelList.add(new PriorityLevel(
                        currentDate,
                        currentDate,
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
