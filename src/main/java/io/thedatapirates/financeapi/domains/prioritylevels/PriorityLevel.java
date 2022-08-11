package io.thedatapirates.financeapi.domains.prioritylevels;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity class to represent a priority level in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class PriorityLevel extends BaseEntity {

    private String level;

    private String description;

    @OneToMany(mappedBy = StringConstants.PRIORITY_LEVEL)
    private List<Expense> expenses = new ArrayList<>();

    public PriorityLevel() {
    }

    public PriorityLevel(LocalDateTime dateCreated, LocalDateTime dateUpdated, String level, String description) {
        super(dateCreated, dateUpdated);
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

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PriorityLevel that = (PriorityLevel) o;
        return Objects.equals(level, that.level) && Objects.equals(description, that.description)
                && Objects.equals(expenses, that.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), level, description, expenses);
    }

    @Override
    public String toString() {
        return "PriorityLevel{" +
                "level='" + level + '\'' +
                ", description='" + description + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
