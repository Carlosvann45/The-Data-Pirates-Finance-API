package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * An object to represent a Data Transfer Object to create a customer
 */
public class RequestCustomerDTO extends BaseEntityDTO {

    @NotBlank(message = StringConstants.FIRST_NAME_NULL)
    private String firstName;

    @NotBlank(message = StringConstants.LAST_NAME_NULL)
    private String lastName;

    @NotBlank(message = StringConstants.USERNAME_NULL)
    @Email(message = StringConstants.USERNAME_BAD_EMAIL)
    private String username;

    @NotBlank(message = StringConstants.PASSWORD_NULL)
    @Size(min = 8, max = 20, message = StringConstants.PASSWORD_BAD_SIZE)
    private String password;

    public RequestCustomerDTO() {
    }

    public RequestCustomerDTO(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public RequestCustomerDTO(Long id, Date dateCreated, Date dateUpdated, String username, String password) {
        super(id, dateCreated, dateUpdated);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
}
