package io.thedatapirates.financeapi.domains.prioritylevels;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface to access and query the database
 */
public interface PriorityLevelRepository extends JpaRepository<PriorityLevel, Long> {

    PriorityLevel findPriorityLevelById(Long id);
}
