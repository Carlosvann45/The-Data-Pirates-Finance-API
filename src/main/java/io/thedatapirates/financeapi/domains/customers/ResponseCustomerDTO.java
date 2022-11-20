package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.domains.cashflows.ResponseCashFlowDTO;
import io.thedatapirates.financeapi.domains.deposits.DepositDTO;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.expenses.ResponseExpenseDTO;
import io.thedatapirates.financeapi.domains.investments.InvestmentDTO;
import io.thedatapirates.financeapi.domains.reminders.ResponseReminderDTO;
import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;

import java.util.ArrayList;
import java.util.List;

/**
 * An object to represent a Data Transfer Object for a customer
 */
public class ResponseCustomerDTO extends BaseEntityDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<InvestmentDTO> investments = new ArrayList<>();

    private List<ResponseCashFlowDTO> cashFlowItems = new ArrayList<>();

    private List<ResponseReminderDTO> reminders = new ArrayList<>();

    private List<ResponseExpenseDTO> expenses = new ArrayList<>();

    private List<DepositDTO> deposits = new ArrayList<>();

    private List<Withdrawal> withdrawals = new ArrayList<>();

    public ResponseCustomerDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<InvestmentDTO> getInvestments() {
        return investments;
    }

    public void setInvestments(List<InvestmentDTO> investments) {
        this.investments = investments;
    }

    public List<ResponseCashFlowDTO> getCashFlowItems() {
        return cashFlowItems;
    }

    public void setCashFlowItems(List<ResponseCashFlowDTO> cashFlowItems) {
        this.cashFlowItems = cashFlowItems;
    }

    public List<ResponseReminderDTO> getReminders() {
        return reminders;
    }

    public void setReminders(List<ResponseReminderDTO> reminders) {
        this.reminders = reminders;
    }

    public List<ResponseExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ResponseExpenseDTO> expenses) {
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

    public List<DepositDTO> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<DepositDTO> deposits) {
        this.deposits = deposits;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<Withdrawal> withdrawals) {
        this.withdrawals = withdrawals;
    }
}
