package io.thedatapirates.financeapi.domains.withdrawals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * Entity class to represent a withdrawal for an expense
 */
@Entity
public class Withdrawal extends BaseEntity {

    private double amount;

    @ManyToOne
    @JoinColumn(name = StringConstants.CUSTOMER_ID, nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = StringConstants.EXPENSE_ID, nullable = false)
    @JsonBackReference
    private Expense expense;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Withdrawal that = (Withdrawal) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(customer, that.customer) && Objects.equals(expense, that.expense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, customer, expense);
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "amount=" + amount +
                ", customer=" + customer +
                ", expense=" + expense +
                '}';
    }
}
