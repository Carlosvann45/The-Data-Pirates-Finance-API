package io.thedatapirates.financeapi.domains.withdrawals;

import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

/**
 * An object to represent a response Data Transfer Object for a withdrawal
 */
public class WithdrawalDTO extends BaseEntityDTO {

    private double amount;

    public WithdrawalDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
