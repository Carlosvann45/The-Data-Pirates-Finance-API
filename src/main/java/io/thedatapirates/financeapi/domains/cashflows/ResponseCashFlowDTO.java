package io.thedatapirates.financeapi.domains.cashflows;

import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;

/**
 * An object to represent a response Data Transfer Object for cash flow items
 */
public class ResponseCashFlowDTO extends BaseEntityDTO {

    private String name;

    private double amount;

    private FrequencyDTO frequency;

    public ResponseCashFlowDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public FrequencyDTO getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyDTO frequency) {
        this.frequency = frequency;
    }
}
