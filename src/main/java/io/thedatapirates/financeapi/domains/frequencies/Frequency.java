package io.thedatapirates.financeapi.domains.frequencies;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.cashflows.CashFlow;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import io.thedatapirates.financeapi.domains.reminders.Reminder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Entity class to represent a frequency in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class Frequency extends BaseEntity {

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = StringConstants.FREQUENCY)
    private List<CashFlow> cashFlow = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = StringConstants.FREQUENCY)
    private List<Reminder> reminders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = StringConstants.FREQUENCY)
    private List<Expense> expenses = new ArrayList<>();

    public Frequency() {
    }

    public Frequency(String name) {
        this.name = name;
    }

    public Frequency(Date dateCreated, Date dateUpdated, String name) {
        super(dateCreated, dateUpdated);
        this.name = name;
    }

    public Frequency(Long id, Date dateCreated, Date dateUpdated, String name, List<CashFlow> cashFlow, List<Reminder> reminders, List<Expense> expenses) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.cashFlow = cashFlow;
        this.reminders = reminders;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CashFlow> getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(List<CashFlow> cashFlow) {
        this.cashFlow = cashFlow;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Frequency frequency = (Frequency) o;
        return Objects.equals(name, frequency.name) && Objects.equals(cashFlow, frequency.cashFlow) && Objects.equals(reminders, frequency.reminders) && Objects.equals(expenses, frequency.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, cashFlow, reminders, expenses);
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "name='" + name + '\'' +
                ", cashFlow=" + cashFlow +
                ", reminders=" + reminders +
                ", expenses=" + expenses +
                '}';
    }
}
