package io.thedatapirates.financeapi.domains.reminders;

import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.expenses.ResponseExpenseDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * An object to represent a response Data Transfer Object for reminders
 */
public class ResponseReminderDTO extends BaseEntityDTO {

    private String name;

    private String description;

    private LocalDateTime reminderTime;

    private FrequencyDTO frequency;

    private ResponseExpenseDTO expense;

    public ResponseReminderDTO() {
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

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public FrequencyDTO getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyDTO frequency) {
        this.frequency = frequency;
    }

    public ResponseExpenseDTO getExpense() {
        return expense;
    }

    public void setExpense(ResponseExpenseDTO expense) {
        this.expense = expense;
    }
}
