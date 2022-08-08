package io.thedatapirates.financeapi.domains.prioritylevels;

import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a priority level
 */
public class PriorityLevelDTO extends BaseEntityDTO {

    private String level;

    private String description;

    public PriorityLevelDTO() {
    }

    public PriorityLevelDTO(String level, String description) {
        this.level = level;
        this.description = description;
    }

    public PriorityLevelDTO(Date dateCreated, Date dateUpdated, String level, String description) {
        super(dateCreated, dateUpdated);
        this.level = level;
        this.description = description;
    }

    public PriorityLevelDTO(Long id, Date dateCreated, Date dateUpdated, String level,
                            String description) {
        super(id, dateCreated, dateUpdated);
        this.level = level;
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
