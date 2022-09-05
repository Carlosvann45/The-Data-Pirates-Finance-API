package io.thedatapirates.financeapi.domains.deposits;

import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

/**
 * An object to represent a response Data Transfer Object for a deposit
 */
public class DepositDTO extends BaseEntityDTO {

    private double amount;

    public DepositDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
