package io.thedatapirates.financeapi.domains.frequencies;


import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a frequency
 */
public class FrequencyDTO extends BaseEntityDTO {

    private String name;

    public FrequencyDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
