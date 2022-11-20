package io.thedatapirates.financeapi.domains.registration;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entities.BaseEntityDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A data transfer object to represent a registration
 */
public class RegistrationDTO extends BaseEntityDTO {

    @NotBlank(message = StringConstants.FIRST_NAME_NULL)
    private String firstName;

    @NotBlank(message = StringConstants.LAST_NAME_NULL)
    private String lastName;

    @NotBlank(message = StringConstants.EMAIL_NULL)
    @Email(message = StringConstants.BAD_EMAIL)
    private String email;

    @NotBlank(message = StringConstants.PASSWORD_NULL)
    @Size(min = 8, max = 20, message = StringConstants.PASSWORD_BAD_SIZE)
    private String password;

    public RegistrationDTO() {
    }

    public RegistrationDTO(Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated, String firstName, String lastName, String email, String password) {
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
        RegistrationDTO that = (RegistrationDTO) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password);
    }
}
