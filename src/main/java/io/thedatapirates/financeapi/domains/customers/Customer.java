package io.thedatapirates.financeapi.domains.customers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.cashflows.CashFlow;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import io.thedatapirates.financeapi.domains.investments.Investment;
import io.thedatapirates.financeapi.domains.reminders.Reminder;
import io.thedatapirates.financeapi.domains.verifications.Verification;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class that represents a customer entity in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<Investment> investments = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<CashFlow> cashFlowItems = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<Reminder> reminders = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    @JsonManagedReference
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    private List<Verification> verifications = new ArrayList<>();

    public Customer() {
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Customer(
            Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated, String username, String password,
            List<Category> categories, List<Investment> investments, List<CashFlow> cashFlowItems,
            List<Reminder> reminders, List<Expense> expenses
    ) {
        super(id, dateCreated, dateUpdated);
        this.username = username;
        this.password = password;
        this.categories = categories;
        this.investments = investments;
        this.cashFlowItems = cashFlowItems;
        this.reminders = reminders;
        this.expenses = expenses;
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

    public List<CashFlow> getCashFlowItems() {
        return cashFlowItems;
    }

    public void setCashFlowItems(List<CashFlow> cashFlowItems) {
        this.cashFlowItems = cashFlowItems;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Verification> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<Verification> verifications) {
        this.verifications = verifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(username, customer.username) && Objects.equals(password, customer.password) && Objects.equals(categories, customer.categories) && Objects.equals(investments, customer.investments) && Objects.equals(cashFlowItems, customer.cashFlowItems) && Objects.equals(reminders, customer.reminders) && Objects.equals(expenses, customer.expenses) && Objects.equals(verifications, customer.verifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, username, password, categories, investments, cashFlowItems, reminders, expenses, verifications);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", categories=" + categories +
                ", investments=" + investments +
                ", cashFlowItems=" + cashFlowItems +
                ", reminders=" + reminders +
                ", expenses=" + expenses +
                ", verifications=" + verifications +
                '}';
    }
}
