package io.thedatapirates.financeapi.domains.expenses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevel;
import io.thedatapirates.financeapi.domains.reminders.Reminder;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * This class that represents a customer entity in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class Expense extends BaseEntity {

    private String name;

    private double amount;

    private Date dueDate;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = StringConstants.REMINDER_ID, referencedColumnName = StringConstants.ID)
    private Reminder reminder = new Reminder();

    public Expense() {
    }

    public Expense(
            Date dateCreated, Date dateUpdated, String name, double amount,
            Date dueDate, Customer customer, Category category, Frequency frequency,
            PriorityLevel priorityLevel, Reminder reminder
    ) {
        super(dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.customer = customer;
        this.category = category;
        this.frequency = frequency;
        this.priorityLevel = priorityLevel;
        this.reminder = reminder;
    }

    public Expense(
            Long id, Date dateCreated, Date dateUpdated,
            String name, double amount, Date dueDate, Customer customer,
            Category category, Frequency frequency, PriorityLevel priorityLevel, Reminder reminder
    ) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.customer = customer;
        this.category = category;
        this.frequency = frequency;
        this.priorityLevel = priorityLevel;
        this.reminder = reminder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 && Objects.equals(name, expense.name) && Objects.equals(dueDate, expense.dueDate) && Objects.equals(customer, expense.customer) && Objects.equals(category, expense.category) && Objects.equals(frequency, expense.frequency) && Objects.equals(priorityLevel, expense.priorityLevel) && Objects.equals(reminder, expense.reminder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, amount, dueDate, customer, category, frequency, priorityLevel, reminder);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", customer=" + customer +
                ", category=" + category +
                ", frequency=" + frequency +
                ", priorityLevel=" + priorityLevel +
                ", reminder=" + reminder +
                '}';
    }
}
