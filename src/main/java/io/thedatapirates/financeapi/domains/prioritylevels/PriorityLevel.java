package io.thedatapirates.financeapi.domains.prioritylevels;

import io.thedatapirates.financeapi.domains.entity.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class to represent a priority level in the database
 */
@Entity
public class PriorityLevel extends BaseEntity {

    private String level;

    private String description;

    public PriorityLevel() {
    }

    public PriorityLevel(String level, String description) {
        this.level = level;
        this.description = description;
    }

    public PriorityLevel(Date dateCreated, Date dateUpdated, String level, String description) {
        super(dateCreated, dateUpdated);
        this.level = level;
        this.description = description;
    }

    public PriorityLevel(Long id, Date dateCreated, Date dateUpdated, String level, String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PriorityLevel that = (PriorityLevel) o;
        return Objects.equals(level, that.level) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), level, description);
    }

    @Override
    public String toString() {
        return "PriorityLevel{" +
                "level='" + level + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
