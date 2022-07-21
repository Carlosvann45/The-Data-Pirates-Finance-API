package io.thedatapirates.financeapi.domains.prioritylevels;

import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a priority level
 */
public class PriorityLevelDTO {

    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    private String level;

    private String description;

    public PriorityLevelDTO() {
    }

    public PriorityLevelDTO(String level, String description) {
        this.level = level;
        this.description = description;
    }

    public PriorityLevelDTO(Date dateCreated, Date dateUpdated, String level, String description) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.level = level;
        this.description = description;
    }

    public PriorityLevelDTO(Long id, Date dateCreated, Date dateUpdated, String level, String description) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.level = level;
        this.description = description;
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
