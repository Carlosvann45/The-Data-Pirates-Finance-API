package io.thedatapirates.financeapi.domains.cashflows;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class to represent a cash flow item in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class CashFlow extends BaseEntity {

    private String name;

    private double amount;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = StringConstants.FREQUENCY_ID, nullable = false)
    private Frequency frequency;

    public CashFlow() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CashFlow cashFlow = (CashFlow) o;
        return Double.compare(cashFlow.amount, amount) == 0 && Objects.equals(name, cashFlow.name)
                && Objects.equals(customer, cashFlow.customer) && Objects.equals(frequency,
                cashFlow.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, amount, customer, frequency);
    }

    @Override
    public String toString() {
        return "CashFlow{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", customer=" + customer +
                ", frequency=" + frequency +
                '}';
    }
}
