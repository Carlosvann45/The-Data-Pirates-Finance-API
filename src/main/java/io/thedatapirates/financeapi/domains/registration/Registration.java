package io.thedatapirates.financeapi.domains.registration;

import io.thedatapirates.financeapi.domains.entities.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An entity class to represent a registration in the database
 */
@Entity
public class Registration extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public Registration() {
    }

    public Registration(Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated, String firstName, String lastName, String email, String password) {
        super(id, dateCreated, dateUpdated);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Registration that = (Registration) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password);
    }
}
