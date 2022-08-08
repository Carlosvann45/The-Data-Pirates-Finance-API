package io.thedatapirates.financeapi.domains.expenses;

import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * A Data Transfer Object to represent an expense response
 */
public class ResponseExpenseDTO extends BaseEntityDTO {

    private String name;

    private double amount;

    private LocalDateTime dueDate;

    private CategoryDTO category;

    private FrequencyDTO frequency;

    private PriorityLevelDTO priorityLevel;

    public ResponseExpenseDTO() {
    }

    public ResponseExpenseDTO(
            LocalDateTime dateCreated, LocalDateTime dateUpdated, String name, double amount,
            LocalDateTime dueDate, CategoryDTO category, FrequencyDTO frequency,
            PriorityLevelDTO priorityLevel
    ) {
        super(dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
        this.frequency = frequency;
        this.priorityLevel = priorityLevel;
    }

    public ResponseExpenseDTO(
            Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated,
            String name, double amount, LocalDateTime dueDate,
            CategoryDTO category, FrequencyDTO frequency,
            PriorityLevelDTO priorityLevel
    ) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
        this.frequency = frequency;
        this.priorityLevel = priorityLevel;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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
}
