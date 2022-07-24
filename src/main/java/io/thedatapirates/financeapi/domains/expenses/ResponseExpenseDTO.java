package io.thedatapirates.financeapi.domains.expenses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevel;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelDTO;
import io.thedatapirates.financeapi.domains.reminders.Reminder;
import io.thedatapirates.financeapi.domains.reminders.ResponseReminderDTO;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * A Data Transfer Object to represent an expense response
 */
public class ResponseExpenseDTO extends BaseEntityDTO {

    private String name;

    private double amount;

    private Date dueDate;

    private CategoryDTO category;

    private FrequencyDTO frequency;

    private PriorityLevelDTO priorityLevel;

    private ResponseReminderDTO reminder = new ResponseReminderDTO();

    public ResponseExpenseDTO() {
    }

    public ResponseExpenseDTO(
            Date dateCreated, Date dateUpdated, String name, double amount,
            Date dueDate, CategoryDTO category, FrequencyDTO frequency,
            PriorityLevelDTO priorityLevel, ResponseReminderDTO reminder
    ) {
        super(dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
        this.frequency = frequency;
        this.priorityLevel = priorityLevel;
        this.reminder = reminder;
    }

    public ResponseExpenseDTO(
            Long id, Date dateCreated, Date dateUpdated,
            String name, double amount, Date dueDate,
            CategoryDTO category, FrequencyDTO frequency,
            PriorityLevelDTO priorityLevel, ResponseReminderDTO reminder
    ) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public FrequencyDTO getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyDTO frequency) {
        this.frequency = frequency;
    }

    public PriorityLevelDTO getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevelDTO priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public ResponseReminderDTO getReminder() {
        return reminder;
    }

    public void setReminder(ResponseReminderDTO reminder) {
        this.reminder = reminder;
    }
}
