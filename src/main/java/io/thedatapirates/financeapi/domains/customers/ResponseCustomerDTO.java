package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.domains.cashflows.ResponseCashFlowDTO;
import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.expenses.ResponseExpenseDTO;
import io.thedatapirates.financeapi.domains.investments.InvestmentDTO;
import io.thedatapirates.financeapi.domains.reminders.ResponseReminderDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An object to represent a Data Transfer Object for a customer
 */
public class ResponseCustomerDTO extends BaseEntityDTO {

  private String username;

  private String password;

  private List<CategoryDTO> categories = new ArrayList<>();

  private List<InvestmentDTO> investments = new ArrayList<>();

  private List<ResponseCashFlowDTO> cashFlowItems = new ArrayList<>();

  private List<ResponseReminderDTO> reminders = new ArrayList<>();

  private List<ResponseExpenseDTO> expenses = new ArrayList<>();

  public ResponseCustomerDTO() {
  }

  public ResponseCustomerDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public ResponseCustomerDTO(
      Long id, Date dateCreated, Date dateUpdated, String username,
      String password, List<CategoryDTO> categories, List<InvestmentDTO> investments,
      List<ResponseCashFlowDTO> cashFlowItems, List<ResponseReminderDTO> reminders,
      List<ResponseExpenseDTO> expenses
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

  public List<CategoryDTO> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryDTO> categories) {
    this.categories = categories;
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
}
