package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;

import java.util.ArrayList;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * An object to represent a Data Transfer Object for a vehicle
 */
public class CustomerDTO {

 private Long id;

 private Date dateCreated;

 private Date dateUpdated;

 @NotBlank(message = StringConstants.USERNAME_NULL)
 @Email(message = StringConstants.USERNAME_BAD_EMAIL)
 private String username;

 @NotBlank(message = StringConstants.PASSWORD_NULL)
 @Size(min = 8, max = 20, message = StringConstants.PASSWORD_BAD_SIZE)
 private String password;

 private List<Category> categories = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomerDTO(Long id, Date dateCreated, Date dateUpdated, String username, String password, List<Category> categories) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.username = username;
        this.password = password;
        this.categories = categories;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
