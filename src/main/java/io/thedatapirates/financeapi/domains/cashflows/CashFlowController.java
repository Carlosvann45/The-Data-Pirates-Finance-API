package io.thedatapirates.financeapi.domains.cashflows;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
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
 * Controller for cash flow items endpoints
 */
@RestController
@RequestMapping(value = Paths.CASH_FLOW_PATH)
public class CashFlowController {

    private final Logger logger = LogManager.getLogger(CashFlowController.class);

    @Autowired
    private CashFlowService cashFlowService;

    /**
     * Gets all cash flow item related to a customer through a bearer token
     *
     * @param token token to get username of customer from
     * @return all cash flow items of a given customer
     */
    @GetMapping
    public ResponseEntity<List<ResponseCashFlowDTO>> getCashFlowByCustomer(
            @RequestHeader(AUTHORIZATION) String token
    ) {
        logger.info(StringConstants.LOG_GET_CASH_FLOW_CUSTOMER);

        List<CashFlow> cashFlowList = cashFlowService.getCashFlowByCustomer(token);

        List<ResponseCashFlowDTO> cashFlowDTOList = cashFlowList
                .stream()
                .map(this::mapCashFlowItemToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(cashFlowDTOList, HttpStatus.OK);
    }

    /**
     * Creates a cash flow item for a customer from bearer token
     *
     * @param token       token to get a customer from
     * @param cashFlowDTO cash flow item to create
     * @return newly created cash flow item
     */
    @PostMapping
    public ResponseEntity<ResponseCashFlowDTO> createCashFlowForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @Valid @RequestBody RequestCashFlowDTO cashFlowDTO
    ) {
        logger.info(StringConstants.LOG_CREATE_CASH_FLOW_CUSTOMER);

        CashFlow cashFlow = mapCashFlowDTOToCasFlow(cashFlowDTO);

        CashFlow newCashFlow = cashFlowService.createCashFlowForCustomer(token, cashFlowDTO.getFrequencyId(), cashFlow);

        ResponseCashFlowDTO newCashFlowDTO = mapCashFlowItemToDTO(newCashFlow);

        return new ResponseEntity<>(newCashFlowDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing cash flow item from a customer bearer token
     *
     * @param token       token to get customer from
     * @param cashFlowId  cash flow id for cash flow to update
     * @param cashFlowDTO updated cash flow
     * @return newly updated cash flow
     */
    @PutMapping(Paths.CASH_FLOW_ID)
    public ResponseEntity<ResponseCashFlowDTO> updateCashFlowForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long cashFlowId,
            @Valid @RequestBody RequestCashFlowDTO cashFlowDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_CASH_FLOW_CUSTOMER);

        CashFlow cashFlow = mapCashFlowDTOToCasFlow(cashFlowDTO);

        CashFlow updatedCashFlow = cashFlowService.updateCashFlowForCustomer(token, cashFlowDTO.getFrequencyId(), cashFlowId, cashFlow);

        ResponseCashFlowDTO updatedCashFlowDTO = mapCashFlowItemToDTO(updatedCashFlow);

        return new ResponseEntity<>(updatedCashFlowDTO, HttpStatus.OK);
    }

    /**
     * Deletes a cash flow from a user with a specified id
     *
     * @param token      token to get user from
     * @param cashFlowId cash flow id to get reminder
     * @return no content
     */
    @DeleteMapping(Paths.CASH_FLOW_ID)
    public ResponseEntity<?> deleteCashFlowForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long cashFlowId
    ) {
        logger.info(StringConstants.LOG_DELETE_CASH_FLOW_CUSTOMER);

        cashFlowService.deleteCashFlowForCustomer(token, cashFlowId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Maps a cash flow item object to a response cash flow DTO object
     *
     * @param cashFlowItem cash flow item to convert
     * @return newly created response cash flow DTO
     */
    private ResponseCashFlowDTO mapCashFlowItemToDTO(CashFlow cashFlowItem) {
        ResponseCashFlowDTO responseCashFlowDTO = new ResponseCashFlowDTO();

        responseCashFlowDTO.setId(cashFlowItem.getId());
        responseCashFlowDTO.setDateCreated(cashFlowItem.getDateCreated());
        responseCashFlowDTO.setDateUpdated(cashFlowItem.getDateUpdated());
        responseCashFlowDTO.setName(cashFlowItem.getName());
        responseCashFlowDTO.setAmount(cashFlowItem.getAmount());

        Frequency frequency = cashFlowItem.getFrequency();
        FrequencyDTO frequencyDTO = new FrequencyDTO();

        frequencyDTO.setId(frequency.getId());
        frequencyDTO.setDateCreated(frequency.getDateCreated());
        frequencyDTO.setDateUpdated(frequency.getDateUpdated());
        frequencyDTO.setName(frequency.getName());

        responseCashFlowDTO.setFrequency(frequencyDTO);

        return responseCashFlowDTO;
    }

    /**
     * Maps a request cash flow DTO object to a cash flow object
     *
     * @param cashFlowDTO cash flow DTO to convert
     * @return newly created cash flow item
     */
    private CashFlow mapCashFlowDTOToCasFlow(RequestCashFlowDTO cashFlowDTO) {
        CashFlow cashFlow = new CashFlow();

        cashFlow.setId(cashFlowDTO.getId());
        cashFlow.setDateCreated(cashFlowDTO.getDateCreated());
        cashFlow.setDateUpdated(cashFlowDTO.getDateUpdated());
        cashFlow.setName(cashFlowDTO.getName());
        cashFlow.setAmount(cashFlowDTO.getAmount());

        return cashFlow;
    }
}
