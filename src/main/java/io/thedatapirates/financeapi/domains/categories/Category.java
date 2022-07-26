package io.thedatapirates.financeapi.domains.categories;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity class to represent a category in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class Category extends BaseEntity {

  private String name;

  @ManyToOne
  @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
  @JsonBackReference
  private Customer customer;

  @OneToMany(mappedBy = StringConstants.CATEGORY)
  private List<Expense> expenses = new ArrayList<>();

  public Category() {
  }

  public Category(String name) {
    this.name = name;
  }

  public Category(Long id, Date dateCreated, Date dateUpdated, String name, Customer customer,
      List<Expense> expenses) {
    super(id, dateCreated, dateUpdated);
    this.name = name;
    this.customer = customer;
    this.expenses = expenses;
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

  public List<Expense> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
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
    Category category = (Category) o;
    return Objects.equals(name, category.name) && Objects.equals(customer, category.customer)
        && Objects.equals(expenses, category.expenses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, customer, expenses);
  }

  @Override
  public String toString() {
    return "Category{" +
        "name='" + name + '\'' +
        ", customer=" + customer +
        ", expenses=" + expenses +
        '}';
  }
}
