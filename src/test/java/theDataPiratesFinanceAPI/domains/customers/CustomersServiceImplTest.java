package theDataPiratesFinanceAPI.domains.customers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import theDataPiratesFinanceAPI.exceptions.ServerUnavailable;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(CustomerServiceImpl.class)
public class CustomersServiceImplTest {

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private CustomerRepository customerRepository;

  Customer testCustomer;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    testCustomer = new Customer("test", "test");

    when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);
  }

  @Test
  public void saveCustomerReturnsCreatedCustomer() {
    //arrange
    Customer expected = testCustomer;

    // act
    Customer actual = customerService.saveCustomer(testCustomer);

    // assert
    assertEquals(expected, actual);
  }

  @Test
  public void getCustomerThrowsServiceUnavailable() {
    // arrange
    when(customerRepository.findAll(any(Example.class))).thenThrow(new DataAccessException("error"){});

    // act & assert
    assertThrows(ServerUnavailable.class, () -> customerService.getCustomers(testCustomer));
  }
}
