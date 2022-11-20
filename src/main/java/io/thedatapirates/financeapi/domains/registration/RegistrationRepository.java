package io.thedatapirates.financeapi.domains.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findRegistrationByEmail(String email);

}
