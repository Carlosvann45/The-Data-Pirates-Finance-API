package io.thedatapirates.financeapi.domains.registration;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.verifications.VerificationService;
import io.thedatapirates.financeapi.utility.MapperExtensions;
import lombok.experimental.ExtensionMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for registration endpoints
 */
@RestController
@RequestMapping(value = Paths.REGISTRATION_PATH)
@ExtensionMethod(MapperExtensions.class)
public class RegistrationController {

    private final Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private VerificationService verificationService;

    /**
     * Creates a registration in the database
     *
     * @param registrationDTO customer to create
     * @return newly created customer
     */
    @PostMapping
    public ResponseEntity<?> registerCustomer(
            @Valid @RequestBody RegistrationDTO registrationDTO) {
        logger.info(StringConstants.LOG_REGISTER_CUSTOMER);

        Registration newRegistration = registrationDTO.mapDTOToRegistration();

        Registration createdRegistration = registrationService.registerCustomer(newRegistration);

        verificationService.sendVerificationEmailForCustomerAccount(createdRegistration);

        return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
    }

}
