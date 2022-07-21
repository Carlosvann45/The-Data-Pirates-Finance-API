package io.thedatapirates.financeapi.domains.prioritylevels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class to represent a priority level in the database
 */
@Entity
public class PriorityLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    private String level;

    private String description;

    public PriorityLevel() {
    }

    public PriorityLevel(String level, String description) {
        this.level = level;
        this.description = description;
    }

    public PriorityLevel(Date dateCreated, Date dateUpdated, String level, String description) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.level = level;
        this.description = description;
    }

    public PriorityLevel(Long id, Date dateCreated, Date dateUpdated, String level, String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityLevel that = (PriorityLevel) o;
        return Objects.equals(id, that.id) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateUpdated, that.dateUpdated) && Objects.equals(level, that.level) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, dateUpdated, level, description);
    }

    @Override
    public String toString() {
        return "PriorityLevel{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
