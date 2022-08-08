package io.thedatapirates.financeapi.domains.categories;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a category
 */
public class CategoryDTO extends BaseEntityDTO {

    @NotBlank(message = StringConstants.NAME_REQUIRED)
    @Size(min = 3, message = StringConstants.NAME_MIN)
    private String name;

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
