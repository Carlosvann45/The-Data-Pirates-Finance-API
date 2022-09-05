package io.thedatapirates.financeapi.domains.cashflows;

import io.thedatapirates.financeapi.domains.deposits.DepositDTO;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An object to represent a response Data Transfer Object for cash flow items
 */
public class ResponseCashFlowDTO extends BaseEntityDTO {

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private FrequencyDTO frequency;

    private List<DepositDTO> deposits = new ArrayList<>();

    public ResponseCashFlowDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FrequencyDTO getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyDTO frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<DepositDTO> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<DepositDTO> deposits) {
        this.deposits = deposits;
    }
}
