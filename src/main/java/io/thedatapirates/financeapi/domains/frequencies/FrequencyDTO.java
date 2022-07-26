package io.thedatapirates.financeapi.domains.frequencies;


import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;
import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a frequency
 */
public class FrequencyDTO extends BaseEntityDTO {

  private String name;

  public FrequencyDTO() {
  }

  public FrequencyDTO(String name) {
    this.name = name;
  }

  public FrequencyDTO(Long id, Date dateCreated, Date dateUpdated, String name) {
    super(id, dateCreated, dateUpdated);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
