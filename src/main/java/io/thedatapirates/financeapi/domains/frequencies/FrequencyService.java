package io.thedatapirates.financeapi.domains.frequencies;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interface class provides abstraction layer for frequency service
 */
public interface FrequencyService {
    List<Frequency> getFrequencies();
    Frequency getFrequencyById(Long id);
}
