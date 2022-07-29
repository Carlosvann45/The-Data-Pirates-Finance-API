package io.thedatapirates.financeapi.domains.frequencies;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.CustomerServiceImpl;
import io.thedatapirates.financeapi.exceptions.NotFound;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A class to implement all methods from the frequency service interface
 */
@Service
public class FrequencyServiceImpl implements FrequencyService {

    private final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private FrequencyRepository frequencyRepository;

    /**
     * Fetches all frequencies from the repository
     *
     * @return all frequencies
     */
    @Override
    public List<Frequency> getFrequencies() {
        try {
            return frequencyRepository.findAll();
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Gets a frequency by id if it exists
     *
     * @param frequencyId frequency id to search for
     * @return frequency with given id
     */
    @Override
    public Frequency getFrequencyById(Long frequencyId) {
        Frequency existingFrequency;

        try {
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingFrequency == null) {
            throw new NotFound(StringConstants.FREQUENCY_NOT_FOUND);
        }

        return existingFrequency;
    }
}
