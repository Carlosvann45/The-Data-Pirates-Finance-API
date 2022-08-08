package io.thedatapirates.financeapi.domains.investments;


import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a investments
 */
public class InvestmentDTO extends BaseEntityDTO {

    private String investmentType;

    @NotBlank(message = StringConstants.NAME_REQUIRED)
    @Size(min = 3, message = StringConstants.NAME_MIN)
    private String name;

    @Range(min = 0)
    private double amount;

    @Range(min = 0)
    private double buyPrice;

    public InvestmentDTO() {
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
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

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
}
