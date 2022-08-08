package io.thedatapirates.financeapi.domains.prioritylevels;

import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

/**
 * An object to represent a Data Transfer Object for a priority level
 */
public class PriorityLevelDTO extends BaseEntityDTO {

    private String level;

    private String description;

    public PriorityLevelDTO() {
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
