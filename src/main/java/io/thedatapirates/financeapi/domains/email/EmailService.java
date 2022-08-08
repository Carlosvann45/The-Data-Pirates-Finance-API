package io.thedatapirates.financeapi.domains.email;

/**
 * Interface class for sending emails
 */
public interface EmailService {
  void sendEmail(String to, String subject,  String email);
}
