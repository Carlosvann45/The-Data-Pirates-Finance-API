package io.thedatapirates.financeapi.domains.categories;

import io.thedatapirates.financeapi.domains.customers.Customer;

import javax.persistence.*;
import java.util.Date;

public class CategoryDTO {

    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    private String name;

    private Customer customer;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO(Long id, Date dateCreated, Date dateUpdated, String name, Customer customer) {
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
}
