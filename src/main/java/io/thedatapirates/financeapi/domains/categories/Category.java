package io.thedatapirates.financeapi.domains.categories;

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
 * Entity class to represent a category in the database
 */
@Entity
public class Category extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
    @JsonBackReference
    private Customer customer;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, Date dateCreated, Date dateUpdated, String name, Customer customer) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(customer, category.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, customer);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", customer=" + customer +
                '}';
    }
}
