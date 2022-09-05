package io.thedatapirates.financeapi.domains.expenses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevel;
import io.thedatapirates.financeapi.domains.reminders.Reminder;
import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Expense extends BaseEntity {

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = StringConstants.CATEGORY_ID, nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = StringConstants.FREQUENCY_ID, nullable = false)
    private Frequency frequency;

    @ManyToOne
    @JoinColumn(name = StringConstants.PRIORITY_LEVEL_ID, nullable = false)
    private PriorityLevel priorityLevel;

    @OneToMany(mappedBy = StringConstants.EXPENSE)
    private List<Reminder> reminders = new ArrayList<>();

    @OneToMany(mappedBy = StringConstants.EXPENSE)
    private  List<Withdrawal> withdrawals = new ArrayList<>();

    public Expense() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
        Expense expense = (Expense) o;
        return Objects.equals(name, expense.name) && Objects.equals(startDate, expense.startDate) && Objects.equals(endDate, expense.endDate) && Objects.equals(customer, expense.customer) && Objects.equals(category, expense.category) && Objects.equals(frequency, expense.frequency) && Objects.equals(priorityLevel, expense.priorityLevel) && Objects.equals(reminders, expense.reminders) && Objects.equals(withdrawals, expense.withdrawals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, startDate, endDate, customer, category, frequency, priorityLevel, reminders, withdrawals);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", customer=" + customer +
                ", category=" + category +
                ", frequency=" + frequency +
                ", priorityLevel=" + priorityLevel +
                ", reminders=" + reminders +
                ", withdrawals=" + withdrawals +
                '}';
    }
}
