package io.thedatapirates.financeapi.domains.verifications;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * Service for verification endpoints
 */
public interface VerificationService {
  void sendVerificationEmailForPassword(@PathVariable String email);
}
