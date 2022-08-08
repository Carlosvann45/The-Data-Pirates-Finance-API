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
public class Verified extends BaseEntity {

    private LocalDateTime dateExpires;

    private LocalDateTime dateConfirmed;

    private String token;

    private String username;

    private VerificationTypes type;

    public Verified() {
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
        Verified verified = (Verified) o;
        return Objects.equals(dateExpires, verified.dateExpires) && Objects.equals(dateConfirmed, verified.dateConfirmed) && Objects.equals(token, verified.token) && Objects.equals(username, verified.username) && type == verified.type;
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
