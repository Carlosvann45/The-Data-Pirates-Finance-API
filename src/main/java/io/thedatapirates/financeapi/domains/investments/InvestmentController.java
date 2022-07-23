package io.thedatapirates.financeapi.domains.investments;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

/**
 * Controller for investment endpoints
 */
@RestController
@RequestMapping(value = Paths.INVESTMENTS_PATH)
public class InvestmentController {

    private final Logger logger = LogManager.getLogger(InvestmentController.class);

    @Autowired
    private InvestmentService investmentService;

    /**
     * Gets all investments related to a customer through a bearer token
     *
     * @param token token to get username of customer from
     * @return all investments of a given customer
     */
    @GetMapping
    public ResponseEntity<List<InvestmentDTO>> getInvestmentsByCustomer(
            @RequestHeader(AUTHORIZATION) String token
    ) {
        logger.info(StringConstants.LOG_GET_INVESTMENTS_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        List<Investment> investments = investmentService.getInvestmentsByCustomer(token);

        List<InvestmentDTO> investmentDTOS = investments
                .stream()
                .map(investment -> mapper.convertValue(investment, InvestmentDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(investmentDTOS, HttpStatus.OK);
    }

    /**
     * Creates an investment for a customer from bearer token
     *
     * @param token       token to get a customer from
     * @param investmentDTO investment to create
     * @return newly created investment
     */
    @PostMapping
    public ResponseEntity<InvestmentDTO> createInvestmentForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @Valid @RequestBody InvestmentDTO investmentDTO
    ) {
        logger.info(StringConstants.LOG_CREATE_INVESTMENTS_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Investment investment = mapper.convertValue(investmentDTO, Investment.class);

        Investment newInvestment = investmentService.createInvestmentForCustomer(token, investment);

        InvestmentDTO newInvestmentDTO = mapper.convertValue(newInvestment, InvestmentDTO.class);

        return new ResponseEntity<>(newInvestmentDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing investment from a customer bearer token
     *
     * @param token       token to get customer from
     * @param investmentId  investment id for investment to update
     * @param investmentDTO updated investment
     * @return newly updated investment
     */
    @PutMapping(Paths.INVESTMENT_ID)
    public ResponseEntity<InvestmentDTO> updateInvestmentForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long investmentId,
            @Valid @RequestBody InvestmentDTO investmentDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_INVESTMENT_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Investment investment = mapper.convertValue(investmentDTO, Investment.class);

        Investment updatedInvestment = investmentService.updateInvestmentForCustomer(token, investmentId, investment);

        InvestmentDTO updatedInvestmentDTO = mapper.convertValue(updatedInvestment, InvestmentDTO.class);

        return new ResponseEntity<>(updatedInvestmentDTO, HttpStatus.OK);
    }

    /**
     * Deletes an investment from a user with a specified id
     *
     * @param token      token to get user from
     * @param investmentId investment id to get investment
     * @return no content
     */
    @DeleteMapping(Paths.INVESTMENT_ID)
    public ResponseEntity<?> deleteInvestmentForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long investmentId
    ) {
        logger.info(StringConstants.LOG_DELETE_INVESTMENT_CUSTOMER);

        investmentService.deleteInvestmentForCustomer(token, investmentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
