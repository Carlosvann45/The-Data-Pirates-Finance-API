package io.thedatapirates.financeapi.domains.reminders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * This class that represents a reminder entity in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class Reminder extends BaseEntity {

  private String name;

  private String description;

  private Date reminderTime;

  @ManyToOne
  @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
  @JsonBackReference
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = StringConstants.FREQUENCY_ID, nullable = false)
  private Frequency frequency;

  @OneToOne(mappedBy = StringConstants.REMINDER)
  private Expense expense;

  public Reminder() {
  }

  public Reminder(
      Date dateCreated, Date dateUpdated, String name, String description,
      Date reminderTime, Customer customer, Frequency frequency, Expense expense
  ) {
    super(dateCreated, dateUpdated);
    this.name = name;
    this.description = description;
    this.reminderTime = reminderTime;
    this.customer = customer;
    this.frequency = frequency;
    this.expense = expense;
  }

  public Reminder(
      Long id, Date dateCreated, Date dateUpdated, String name, String description,
      Date reminderTime, Customer customer, Frequency frequency, Expense expense
  ) {
    super(id, dateCreated, dateUpdated);
    this.name = name;
    this.description = description;
    this.reminderTime = reminderTime;
    this.customer = customer;
    this.frequency = frequency;
    this.expense = expense;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getReminderTime() {
    return reminderTime;
  }

  public void setReminderTime(Date reminderTime) {
    this.reminderTime = reminderTime;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
  }

  public Expense getExpense() {
    return expense;
  }

  public void setExpense(Expense expense) {
    this.expense = expense;
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
    Reminder reminder = (Reminder) o;
    return Objects.equals(name, reminder.name) && Objects.equals(description, reminder.description)
        && Objects.equals(reminderTime, reminder.reminderTime)
        && Objects.equals(customer, reminder.customer)
        && Objects.equals(frequency, reminder.frequency) && Objects.equals(expense,
        reminder.expense);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, reminderTime, customer, frequency,
        expense);
  }

  @Override
  public String toString() {
    return "Reminder{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", reminderTime=" + reminderTime +
        ", customer=" + customer +
        ", frequency=" + frequency +
        ", expense=" + expense +
        '}';
  }
}
