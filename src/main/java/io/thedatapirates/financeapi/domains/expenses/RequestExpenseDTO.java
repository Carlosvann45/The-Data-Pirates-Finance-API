package io.thedatapirates.financeapi.domains.expenses;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDate;

    @Range(min = 1, max = 11)
    private Long categoryId;

    @Range(min = 1, max = 5)
    private Long frequencyId;

    @Range(min = 1, max = 4)
    private Long priorityLevelId;

    public RequestExpenseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
