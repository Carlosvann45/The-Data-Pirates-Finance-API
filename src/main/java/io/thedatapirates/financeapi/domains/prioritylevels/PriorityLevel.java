package io.thedatapirates.financeapi.domains.prioritylevels;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entity.BaseEntity;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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

  public PriorityLevel(String level, String description) {
    this.level = level;
    this.description = description;
  }

  public PriorityLevel(Date dateCreated, Date dateUpdated, String level, String description) {
    super(dateCreated, dateUpdated);
    this.level = level;
    this.description = description;
  }

  public PriorityLevel(Long id, Date dateCreated, Date dateUpdated, String level,
      String description, List<Expense> expenses) {
    super(id, dateCreated, dateUpdated);
    this.level = level;
    this.description = description;
    this.expenses = expenses;
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
