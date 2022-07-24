package io.thedatapirates.financeapi.domains.frequencies;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for frequency endpoints
 */
@RestController
@RequestMapping(value = Paths.FREQUENCY_PATH)
public class FrequencyController {

    private final Logger logger = LogManager.getLogger(FrequencyController.class);

    @Autowired
    private FrequencyService frequencyService;

    /**
     * Retrieves all frequencies
     *
     * @return a list of frequencies
     */
    @GetMapping
    public ResponseEntity<List<FrequencyDTO>> getFrequencies() {
        logger.info(StringConstants.LOG_GET_FREQUENCIES);

        List<Frequency> frequencies = frequencyService.getFrequencies();

        List<FrequencyDTO> frequencyDTOS = frequencies
                .stream()
                .map(this::mapFrequencyToFrequencyDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(frequencyDTOS, HttpStatus.OK);
    }

    /**
     * Get a frequency by a given id
     *
     * @param frequencyId frequency id to search for
     * @return frequency with given id
     */
    @GetMapping(Paths.FREQUENCY_ID)
    public ResponseEntity<FrequencyDTO> getFrequencyById(@PathVariable Long frequencyId) {
        logger.info(StringConstants.LOG_GET_FREQUENCIES_ID);

        Frequency frequency = frequencyService.getFrequencyById(frequencyId);

        FrequencyDTO frequencyDTO = mapFrequencyToFrequencyDTO(frequency);

        return new ResponseEntity<>(frequencyDTO, HttpStatus.OK);
    }

    /**
     * Maps a frequency to a frequency DTO
     *
     * @param frequency frequency to map
     * @return newly created frequency DTO
     */
    private FrequencyDTO mapFrequencyToFrequencyDTO(Frequency frequency) {
        FrequencyDTO frequencyDTO = new FrequencyDTO();

        frequencyDTO.setId(frequency.getId());
        frequencyDTO.setDateCreated(frequency.getDateCreated());
        frequencyDTO.setDateUpdated(frequency.getDateUpdated());
        frequencyDTO.setName(frequency.getName());

        return frequencyDTO;
    }
}
