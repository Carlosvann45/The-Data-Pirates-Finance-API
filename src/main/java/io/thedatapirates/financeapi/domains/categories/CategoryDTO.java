package io.thedatapirates.financeapi.domains.categories;

import io.thedatapirates.financeapi.constants.StringConstants;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CategoryDTO {

    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    @NotBlank(message = StringConstants.NAME_REQUIRED)
    @Min(value = 3, message = StringConstants.NAME_MIN)
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO(Long id, Date dateCreated, Date dateUpdated, String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
