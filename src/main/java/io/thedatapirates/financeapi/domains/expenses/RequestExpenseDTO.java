package io.thedatapirates.financeapi.domains.expenses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;

import java.util.Date;

/**
 * A Data Transfer Object to represent an expense request
 */
public class RequestExpenseDTO extends BaseEntityDTO {

    private String name;

    private double amount;

    private Date dueDate;

    @JsonIgnore
    private Long categoryId;

    @JsonIgnore
    private Long frequencyId;

    @JsonIgnore
    private Long priorityLevelId;

    public RequestExpenseDTO() {
    }

    public RequestExpenseDTO(
            Date dateCreated, Date dateUpdated, String name, double amount,
            Date dueDate, Long categoryId, Long frequencyId, Long priorityLevelId
    ) {
        super(dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.categoryId = categoryId;
        this.frequencyId = frequencyId;
        this.priorityLevelId = priorityLevelId;
    }

    public RequestExpenseDTO(
            Long id, Date dateCreated, Date dateUpdated, String name, double amount,
            Date dueDate, Long categoryId, Long frequencyId, Long priorityLevelId
    ) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.categoryId = categoryId;
        this.frequencyId = frequencyId;
        this.priorityLevelId = priorityLevelId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Long frequencyId) {
        this.frequencyId = frequencyId;
    }

    public Long getPriorityLevelId() {
        return priorityLevelId;
    }

    public void setPriorityLevelId(Long priorityLevelId) {
        this.priorityLevelId = priorityLevelId;
    }
}
