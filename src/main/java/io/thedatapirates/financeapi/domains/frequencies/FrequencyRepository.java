package io.thedatapirates.financeapi.domains.frequencies;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface to access and query the database
 */
public interface FrequencyRepository extends JpaRepository<Frequency, Long> {

    Frequency findFrequencyById(Long id);
}
