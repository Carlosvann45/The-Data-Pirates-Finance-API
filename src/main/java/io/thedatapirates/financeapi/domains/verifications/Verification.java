package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.utility.VerificationTypes;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An entity class to represent whether a customer was verified for a specific type.
 */
@Entity
public class Verification extends BaseEntity {

    private LocalDateTime dateExpires;

    private LocalDateTime dateConfirmed;

    private String token;

    private VerificationTypes type;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID)
    private Customer customer;


    public Verification() {
    }

    public LocalDateTime getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(LocalDateTime dateExpires) {
        this.dateExpires = dateExpires;
    }

    public LocalDateTime getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(LocalDateTime dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VerificationTypes getType() {
        return type;
    }

    public void setType(VerificationTypes type) {
        this.type = type;
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
        Verification that = (Verification) o;
        return Objects.equals(dateExpires, that.dateExpires) && Objects.equals(dateConfirmed, that.dateConfirmed) && Objects.equals(token, that.token) && type == that.type && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateExpires, dateConfirmed, token, type, customer);
    }

    @Override
    public String toString() {
        return "Verification{" +
                "dateExpires=" + dateExpires +
                ", dateConfirmed=" + dateConfirmed +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", customer=" + customer +
                '}';
    }
}
