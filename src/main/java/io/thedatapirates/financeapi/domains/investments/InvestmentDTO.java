package io.thedatapirates.financeapi.domains.investments;


import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

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

  public InvestmentDTO(Date dateCreated, Date dateUpdated, String investmentType, String name,
      double amount, double buyPrice) {
    super(dateCreated, dateUpdated);
    this.investmentType = investmentType;
    this.name = name;
    this.amount = amount;
    this.buyPrice = buyPrice;
  }

  public InvestmentDTO(Long id, Date dateCreated, Date dateUpdated, String investmentType,
      String name, double amount, double buyPrice) {
    super(id, dateCreated, dateUpdated);
    this.investmentType = investmentType;
    this.name = name;
    this.amount = amount;
    this.buyPrice = buyPrice;
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
