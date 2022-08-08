package io.thedatapirates.financeapi.domains.email;

import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * An email service class to send out emails
 */
@Service
public class EmailServiceImpl implements EmailService{

  private final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

  @Autowired
  private JavaMailSender mailSender;

  /**
   * Sends an email to selected account with subject and email
   * @param to person to send the email to
   * @param subject subject of the email
   * @param email body of the email
   */
  @Override
  public void sendEmail(String to, String subject,  String email) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

      messageHelper.setText(email, true);
      messageHelper.setTo(to);
      messageHelper.setSubject(subject);
      messageHelper.setFrom("thedatapirates.cashplan@gmail.com");

      mailSender.send(mimeMessage);

    } catch (MessagingException e) {
        logger.error(e.getMessage());

        throw new ServerUnavailable(e.getMessage());
      }
    }

  }
