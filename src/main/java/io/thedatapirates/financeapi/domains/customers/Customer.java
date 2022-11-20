package io.thedatapirates.financeapi.domains.customers;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.cashflows.CashFlow;
import io.thedatapirates.financeapi.domains.deposits.Deposit;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import io.thedatapirates.financeapi.domains.investments.Investment;
import io.thedatapirates.financeapi.domains.reminders.Reminder;
import io.thedatapirates.financeapi.domains.verifications.Verification;
import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;

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

    private String email;

    private String password;

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

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    private List<Deposit> deposits = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.CUSTOMER)
    private List<Withdrawal> withdrawals = new ArrayList<>();

    public Customer() {
    }

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer(
            Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated, String email, String password,
            List<Investment> investments, List<CashFlow> cashFlowItems,
            List<Reminder> reminders, List<Expense> expenses
    ) {
        super(id, dateCreated, dateUpdated);
        this.email = email;
        this.password = password;
        this.investments = investments;
        this.cashFlowItems = cashFlowItems;
        this.reminders = reminders;
        this.expenses = expenses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<Withdrawal> withdrawals) {
        this.withdrawals = withdrawals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password) && Objects.equals(investments, customer.investments) && Objects.equals(cashFlowItems, customer.cashFlowItems) && Objects.equals(reminders, customer.reminders) && Objects.equals(expenses, customer.expenses) && Objects.equals(verifications, customer.verifications) && Objects.equals(deposits, customer.deposits) && Objects.equals(withdrawals, customer.withdrawals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password, investments, cashFlowItems, reminders, expenses, verifications, deposits, withdrawals);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + email + '\'' +
                ", password='" + password + '\'' +
                ", investments=" + investments +
                ", cashFlowItems=" + cashFlowItems +
                ", reminders=" + reminders +
                ", expenses=" + expenses +
                ", verifications=" + verifications +
                ", deposits=" + deposits +
                ", withdrawals=" + withdrawals +
                '}';
    }
}
