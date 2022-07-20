package io.thedatapirates.financeapi.domains.categories;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

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
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.name = name;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
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
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(dateCreated, category.dateCreated) && Objects.equals(dateUpdated, category.dateUpdated) && Objects.equals(name, category.name) && Objects.equals(customer, category.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, dateUpdated, name, customer);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", name='" + name + '\'' +
                ", customer=" + customer +
                '}';
    }
}
