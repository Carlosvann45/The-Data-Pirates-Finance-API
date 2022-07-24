package io.thedatapirates.financeapi.domains.cashflows;

import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;

import java.util.Date;

/**
 * An object to represent a request Data Transfer Object for cash flow items
 */
public class RequestCashFlowDTO extends BaseEntityDTO {

    private String name;

    private double amount;

    private Long frequencyId;

    public RequestCashFlowDTO() {
    }

    public RequestCashFlowDTO(Date dateCreated, Date dateUpdated) {
        super(dateCreated, dateUpdated);
    }

    public RequestCashFlowDTO(Long id, Date dateCreated, Date dateUpdated, String name, double amount, Long frequencyId) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.frequencyId = frequencyId;
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

    public Long getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Long frequencyId) {
        this.frequencyId = frequencyId;
    }
}
