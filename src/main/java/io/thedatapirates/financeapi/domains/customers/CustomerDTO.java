package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;

import java.util.ArrayList;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * An object to represent a Data Transfer Object for a customer
 */
public class CustomerDTO extends BaseEntityDTO {

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
        super(id, dateCreated, dateUpdated);
        this.username = username;
        this.password = password;
        this.categories = categories;
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
