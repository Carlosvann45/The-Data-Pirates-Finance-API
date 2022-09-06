package io.thedatapirates.financeapi.domains.cashflows;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * An object to represent a request Data Transfer Object for cash flow items
 */
public class RequestCashFlowDTO extends BaseEntityDTO {

<<<<<<< Updated upstream
=======
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDate;

>>>>>>> Stashed changes
    @NotBlank(message = StringConstants.NAME_REQUIRED)
    @Size(min = 3, message = StringConstants.NAME_MIN)
    private String name;

<<<<<<< Updated upstream
    @Range(min = 0)
    private double amount;

    @Range(min = 1, max = 4)
=======
    @Range(min = 1, max = 5)
>>>>>>> Stashed changes
    private Long frequencyId;

    public RequestCashFlowDTO() {
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

    public Long getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Long frequencyId) {
        this.frequencyId = frequencyId;
    }
}
