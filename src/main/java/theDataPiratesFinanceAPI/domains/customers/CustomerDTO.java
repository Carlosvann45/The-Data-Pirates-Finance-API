package theDataPiratesFinanceAPI.domains.customers;

/**
 * An object to represent a Data Transfer Object for a vehicle
 */
public class CustomerDTO {

  private Long id;

 private String username;

 private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public CustomerDTO() { }

  public CustomerDTO(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }
}
