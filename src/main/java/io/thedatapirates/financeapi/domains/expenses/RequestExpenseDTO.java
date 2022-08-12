package io.thedatapirates.financeapi.domains.expenses;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * A Data Transfer Object to represent an expense request
 */
public class RequestExpenseDTO extends BaseEntityDTO {

    @NotBlank(message = StringConstants.NAME_REQUIRED)
    @Size(min = 3, message = StringConstants.NAME_MIN)
    private String name;

    @Range(min = 0)
    private double amount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dueDate;

    @Range(min = 0)
    private Long categoryId;

    @Range(min = 1, max = 4)
    private Long frequencyId;

    @Range(min = 1, max = 4)
    private Long priorityLevelId;

    public RequestExpenseDTO() {
    }

    public RequestExpenseDTO(
            LocalDateTime dateCreated, LocalDateTime dateUpdated, String name, double amount,
            LocalDateTime dueDate, Long categoryId, Long frequencyId, Long priorityLevelId
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
            Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated, String name, double amount,
            LocalDateTime dueDate, Long categoryId, Long frequencyId, Long priorityLevelId
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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
