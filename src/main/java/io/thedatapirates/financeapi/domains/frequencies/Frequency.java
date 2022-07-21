package io.thedatapirates.financeapi.domains.frequencies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class to represent a frequency in the database
 */
@Entity
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    private String name;

    public Frequency() {
    }

    public Frequency(String name) {
        this.name = name;
    }

    public Frequency(Date dateCreated, Date dateUpdated, String name) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.name = name;
    }

    public Frequency(Long id, Date dateCreated, Date dateUpdated, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequency frequency = (Frequency) o;
        return Objects.equals(id, frequency.id) && Objects.equals(dateCreated, frequency.dateCreated) && Objects.equals(dateUpdated, frequency.dateUpdated) && Objects.equals(name, frequency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, dateUpdated, name);
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", name='" + name + '\'' +
                '}';
    }
}
