package io.thedatapirates.financeapi.domains.frequencies;

import io.thedatapirates.financeapi.domains.entity.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class to represent a frequency in the database
 */
@Entity
public class Frequency extends BaseEntity {

    private String name;

    public Frequency() {
    }

    public Frequency(String name) {
        this.name = name;
    }

    public Frequency(Date dateCreated, Date dateUpdated, String name) {
        super(dateCreated, dateUpdated);
        this.name = name;
    }

    public Frequency(Long id, Date dateCreated, Date dateUpdated, String name) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Frequency frequency = (Frequency) o;
        return Objects.equals(name, frequency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "name='" + name + '\'' +
                '}';
    }
}
