package io.thedatapirates.financeapi.domains.customers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;
import io.thedatapirates.financeapi.domains.investments.Investment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * This class that represents a customer entity in the database
 */
@Entity
public class Customer extends BaseEntity {

    private String username;

    private String password;

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<Investment> investments = new ArrayList<>();

    public Customer() {
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(Long id, Date dateCreated, Date dateUpdated, String username, String password, List<Category> categories, List<Investment> investments) {
        super(id, dateCreated, dateUpdated);
        this.username = username;
        this.password = password;
        this.categories = categories;
        this.investments = investments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(username, customer.username) && Objects.equals(password, customer.password) && Objects.equals(categories, customer.categories) && Objects.equals(investments, customer.investments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, categories, investments);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", categories=" + categories +
                ", investments=" + investments +
                '}';
    }
}
