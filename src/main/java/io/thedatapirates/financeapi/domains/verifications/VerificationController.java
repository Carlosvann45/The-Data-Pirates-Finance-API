package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;

/**
 * Controller for verification endpoints
 */
@Controller
@RequestMapping(value = Paths.VERIFICATION_PATH)
public class VerificationController {

    private final Logger logger = LogManager.getLogger(VerificationController.class);

    @Autowired
    private VerificationService verificationService;

    /**
     * Request to get an email to reset password to an existing account
     *
     * @param email email to send password reset link to
     * @return response for the email being created
     */
    @PostMapping(Paths.FORGOT_PASS_PATH)
    @ResponseBody
    public ResponseEntity<?> sendVerificationEmailForPassword(
            @PathVariable
            @Email(message = StringConstants.EMAIL_INVALID)
                    String email) {
        logger.info(StringConstants.LOG_SEND_PASSWORD_VERIFICATION_EMAIL);

        verificationService.sendVerificationEmailForPassword(email);

        // always return 201 so attackers don't know if email exists or not
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Handles showing user the reset password
     * @param token token to verify verification object
     * @param model model for displaying html
     * @return html page for customer to fill out
     */
    @GetMapping(Paths.CHANGE_PASS_PATH)
    public String changePasswordWithToken(@RequestParam String token, Model model) {
        logger.info(StringConstants.LOG_CHANGE_PASSWORD_VERIFICATION);

        try {

            verificationService.changePasswordWithToken(token);
        } catch (Exception ex) {
            return "error_page";
        }

        model.addAttribute("token", token);

        return "reset_password_form";
    }

    /**
     * Updates the customer's password after form is filled out
     *
     * @param request request to get parameters from
     * @param model model for displaying next screen
     * @return html page for cusotmer to view
     */
    @PostMapping(Paths.CHANGE_PASS_PATH)
    public String updatePasswordOnCustomer(HttpServletRequest request, Model model) {
        logger.info(StringConstants.LOG_POST_CHANGE_PASSWORD_VERIFICATION);

        String token = request.getParameter("token");
        String password = request.getParameter("password");

        try {
            verificationService.updatePasswordOnCustomer(token, password);
        } catch (Exception ex) {
            return "error_page";
        }

        return "confirmation_page";
    }
}
