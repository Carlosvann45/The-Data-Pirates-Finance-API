package io.thedatapirates.financeapi.domains.expenses;

import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelDTO;
import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;
import io.thedatapirates.financeapi.domains.withdrawals.WithdrawalDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Data Transfer Object to represent an expense response
 */
public class ResponseExpenseDTO extends BaseEntityDTO {

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private CategoryDTO category;

    private FrequencyDTO frequency;

    private PriorityLevelDTO priorityLevel;

    private List<WithdrawalDTO> withdrawals = new ArrayList<>();

    public ResponseExpenseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<WithdrawalDTO> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<WithdrawalDTO> withdrawals) {
        this.withdrawals = withdrawals;
    }
}
