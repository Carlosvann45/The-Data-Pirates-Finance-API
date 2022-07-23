package io.thedatapirates.financeapi.domains.customers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;

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

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Category> categories = new ArrayList<>();

    public Customer() {
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(Date dateCreated, Date dateUpdated, String username, String password, List<Category> categories) {
        super(dateCreated, dateUpdated);
        this.username = username;
        this.password = password;
        this.categories = categories;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(username, customer.username) && Objects.equals(password, customer.password) && Objects.equals(categories, customer.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, categories);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", categories=" + categories +
                '}';
    }
}
