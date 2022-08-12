package io.thedatapirates.financeapi.domains.verifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Verification findVerificationByToken(String token);
}
