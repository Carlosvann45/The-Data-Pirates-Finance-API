package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import javax.validation.constraints.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for verification endpoints
 */
@RestController
@RequestMapping(value = Paths.VERIFICATION_PATH)
public class VerificationController {

  private final Logger logger = LogManager.getLogger(VerificationController.class);

  @Autowired
  private VerificationService verificationService;

  @PostMapping(Paths.FORGOT_PASS_PATH)
  public ResponseEntity<?> sendVerificationEmailForPassword(
      @PathVariable
      @Email(message = StringConstants.EMAIL_INVALID)
          String email) {
    logger.info(StringConstants.LOG_SEND_PASSWORD_VERIFICATION_EMAIL);

    verificationService.sendVerificationEmailForPassword(email);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(Paths.CHANGE_PASS_PATH)
  public ResponseEntity<String> changePasswordWithToken(@RequestParam String token) {
  return new ResponseEntity<>("Confirm Your password", HttpStatus.OK);
  }

}
