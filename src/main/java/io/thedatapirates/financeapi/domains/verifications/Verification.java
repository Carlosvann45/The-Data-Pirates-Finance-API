package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.domains.entities.BaseEntity;
import io.thedatapirates.financeapi.utility.VerificationTypes;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An entity class to represent whether a customer was verified for a specific type.
 */
@Entity
public class Verification extends BaseEntity {

    private LocalDateTime dateExpires;

    private LocalDateTime dateConfirmed;

    private String token;

    private String username;

    private VerificationTypes type;

    public Verification() {
    }

    public LocalDateTime getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(LocalDateTime dateExpires) {
        this.dateExpires = dateExpires;
    }

    public LocalDateTime getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(LocalDateTime dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VerificationTypes getType() {
        return type;
    }

    public void setType(VerificationTypes type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Verification verification = (Verification) o;
        return Objects.equals(dateExpires, verification.dateExpires) && Objects.equals(dateConfirmed, verification.dateConfirmed) && Objects.equals(token, verification.token) && Objects.equals(username, verification.username) && type == verification.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateExpires, dateConfirmed, token, username, type);
    }

    @Override
    public String toString() {
        return "Verified{" +
                "dateExpires=" + dateExpires +
                ", dateConfirmed=" + dateConfirmed +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", type=" + type +
                '}';
    }
}
