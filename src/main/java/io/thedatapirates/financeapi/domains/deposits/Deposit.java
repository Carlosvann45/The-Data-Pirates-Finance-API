package io.thedatapirates.financeapi.domains.deposits;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.cashflows.CashFlow;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * Entity class to represent a deposit for a cash flow item
 */
@Entity
public class Deposit extends BaseEntity {

    private double amount;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = StringConstants.CASH_FLOW_ID, nullable = false)
    @JsonBackReference
    private CashFlow cashFlow;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CashFlow getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(CashFlow cashFlow) {
        this.cashFlow = cashFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deposit deposit = (Deposit) o;
        return Double.compare(deposit.amount, amount) == 0 && Objects.equals(customer, deposit.customer) && Objects.equals(cashFlow, deposit.cashFlow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, customer, cashFlow);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "amount=" + amount +
                ", customer=" + customer +
                ", cashFlow=" + cashFlow +
                '}';
    }
}
