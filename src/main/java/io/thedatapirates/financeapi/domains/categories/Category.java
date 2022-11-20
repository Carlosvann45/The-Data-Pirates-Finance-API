package io.thedatapirates.financeapi.domains.categories;

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
 * Entity class to represent a category in the database
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = StringConstants.ID)
public class Category extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = StringConstants.CATEGORY)
    private List<Expense> expenses = new ArrayList<>();

    public Category() {
    }

    public Category(LocalDateTime dateCreated, LocalDateTime dateUpdated, String name) {
        super(dateCreated, dateUpdated);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(expenses, category.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, expenses);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
