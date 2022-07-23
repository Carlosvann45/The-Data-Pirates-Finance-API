package io.thedatapirates.financeapi.domains.investments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

/**
 * A class to represent investments for a customer in the database
 */
@Entity
public class Investment extends BaseEntity {

    private String investmentType;

    private String name;

    private double amount;

    private double buyPrice;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
    @JsonBackReference
    private Customer customer;

    public Investment() {
    }

    public Investment(Date dateCreated, Date dateUpdated, String investmentType, String name, double amount, double buyPrice, Customer customer) {
        super(dateCreated, dateUpdated);
        this.investmentType = investmentType;
        this.name = name;
        this.amount = amount;
        this.buyPrice = buyPrice;
        this.customer = customer;
    }

    public Investment(Long id, Date dateCreated, Date dateUpdated, String investmentType, String name, double amount, double buyPrice, Customer customer) {
        super(id, dateCreated, dateUpdated);
        this.investmentType = investmentType;
        this.name = name;
        this.amount = amount;
        this.buyPrice = buyPrice;
        this.customer = customer;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
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

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Investment that = (Investment) o;
        return Double.compare(that.amount, amount) == 0 && Double.compare(that.buyPrice, buyPrice) == 0 && Objects.equals(investmentType, that.investmentType) && Objects.equals(name, that.name) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), investmentType, name, amount, buyPrice, customer);
    }

    @Override
    public String toString() {
        return "Investment{" +
                "investmentType='" + investmentType + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", buyPrice=" + buyPrice +
                ", customer=" + customer +
                '}';
    }
}
