package theDataPiratesFinanceAPI.domains.customers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static theDataPiratesFinanceAPI.constants.Paths.CUSTOMER_PATH;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomersApiTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	@Test
	public void getCustomersReturns200() throws Exception {
		mockMvc.perform(get(CUSTOMER_PATH)).andExpect(status().isOk());
	}

	@Test
	public void saveCustomersReturns400() throws Exception {
		// object mapper for creating a json string
		ObjectMapper mapper = new ObjectMapper();

		// test customer dto
		CustomerDTO testCustomer = new CustomerDTO();

		// converts customer DTO to json string
		String JsonString = mapper.writeValueAsString(testCustomer);

		mockMvc.perform(post(CUSTOMER_PATH)
				.content(JsonString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
