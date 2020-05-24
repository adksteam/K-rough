package com.example.Customer.ControllerTest;

//Unit Testing of Controller Module

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Customer.Controller.CustomerController;
import com.example.Customer.ErrorHandler.ErrorResponse;
import com.example.Customer.Model.Customer;
import com.example.Customer.Service.CustomerService;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_ = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	@MockBean
	private CustomerService customerService;

	@MockBean
	private ErrorResponse errorResponse;

	@Autowired
	private WebApplicationContext webApplicationContext;

	// Convert object to json
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	@Before
	public void setUp() {
		Mockito.reset(customerService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/*
	 * @Rule public ExpectedException thrown = ExpectedException.none();
	 * 
	 * @Test public void getAllCustomersNoCustomerFoundError() throws Exception {
	 * 
	 * errorResponse.setTimeStamp(new Date());
	 * errorResponse.setMessage("No Record Found."); List<String> details=new
	 * ArrayList<String>(); details.add("No Customer Found.");
	 * errorResponse.setDetails(details); try {
	 * Mockito.when(customerService.getAllCustomers()) .thenThrow(new
	 * ResourceNotFoundException("No Customer Found.")); }
	 * catch(ResourceNotFoundException ex) { assertThat(e.getCode(),
	 * is(ResourceNotFoundException)); }
	 * 
	 * 
	 * verify(customerService, times(1)).getAllCustomers();
	 * verifyNoMoreInteractions(customerService); }
	 */
	// Test to check getAllCustomers
	@Test
	public void getAllCustomersTest() throws Exception {
		Customer testCustomer = new Customer(1L, "Kushagra Tiwari", "kushagra@gmail.com", "Itarsi, MP",
				"Savings Account", "DB1234567890", 100.00);

		Mockito.when(customerService.getAllCustomers()).thenReturn(Arrays.asList(testCustomer));

		mockMvc.perform(get("/Customers")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_)).andExpect(jsonPath("$[*]", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Kushagra Tiwari")))
				.andExpect(jsonPath("$[0].email", is("kushagra@gmail.com")))
				.andExpect(jsonPath("$[0].address", is("Itarsi, MP")))
				.andExpect(jsonPath("$[0].accountType", is("Savings Account")))
				.andExpect(jsonPath("$[0].accountNo", is("DB1234567890")))
				.andExpect(jsonPath("$[0].accountBalance", is(100.00)));
		verify(customerService, times(1)).getAllCustomers();
		verifyNoMoreInteractions(customerService);

	}

	// Test to check getCustomerById
	@Test
	public void getCustomerByIdTest() throws Exception {
		Customer testCustomer = new Customer(1L, "Kushagra Tiwari", "kushagra@gmail.com", "Itarsi, MP",
				"Savings Account", "DB1234567890", 100.00);

		Mockito.<Optional<Customer>>when(customerService.getCustomerById(Mockito.anyLong()))
				.thenReturn(Optional.of(testCustomer));

		mockMvc.perform(get("/Customers/{id}", Mockito.anyLong())).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_)).andExpect(jsonPath("$.*", hasSize(7)))
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is("Kushagra Tiwari")))
				.andExpect(jsonPath("$.email", is("kushagra@gmail.com")))
				.andExpect(jsonPath("$.address", is("Itarsi, MP")))
				.andExpect(jsonPath("$.accountType", is("Savings Account")))
				.andExpect(jsonPath("$.accountNo", is("DB1234567890")))
				.andExpect(jsonPath("$.accountBalance", is(100.00)));
		verify(customerService, times(1)).getCustomerById(Mockito.anyLong());
		verifyNoMoreInteractions(customerService);

	}

	// Test for createCustomer()
	@Test
	public void createCustomerTest() throws Exception {

		Customer customerRequest = new Customer();
		customerRequest.setName("Kushagra Tiwari");
		customerRequest.setAddress("Itarsi, MP");
		customerRequest.setEmail("kushagra@gmail.com");
		customerRequest.setAccountNo("DB1234567890");
		customerRequest.setAccountType("Savings Account");
		customerRequest.setAccountBalance(100.00);

		Customer customerResponse = new Customer(1L, "Kushagra Tiwari", "kushagra@gmail.com", "Itarsi, MP",
				"Savings Account", "DB1234567890", 100.00);

		Mockito.when(customerService.createNewCustomer(Mockito.any(Customer.class))).thenReturn(customerResponse);

		mockMvc.perform(
				post("/Customers").contentType(APPLICATION_JSON_).content(convertObjectToJsonBytes(customerRequest)))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_))
				.andExpect(jsonPath("$.*", hasSize(7))).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Kushagra Tiwari")))
				.andExpect(jsonPath("$.email", is("kushagra@gmail.com")))
				.andExpect(jsonPath("$.address", is("Itarsi, MP")))
				.andExpect(jsonPath("$.accountType", is("Savings Account")))
				.andExpect(jsonPath("$.accountNo", is("DB1234567890")))
				.andExpect(jsonPath("$.accountBalance", is(100.00)));

		ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
		verify(customerService, times(1)).createNewCustomer(customerCaptor.capture());
		verifyNoMoreInteractions(customerService);

	}

	// Test for updateCustomerById()
	@Test
	public void updateCustomerByIdTest() throws Exception {

		Customer customerRequest = new Customer();
		customerRequest.setName("Kushagra Tiwari");
		customerRequest.setAddress("Itarsi, MP");
		customerRequest.setEmail("kushagra@gmail.com");
		customerRequest.setAccountNo("DB1234567890");
		customerRequest.setAccountType("Savings Account");
		customerRequest.setAccountBalance(100.00);

		Customer customerResponse = new Customer(1L, "Kushagra Tiwari", "kushagra@gmail.com", "Bhopal, MP",
				"Current Account", "DB1234567890", 1000.00);
		Mockito.<Optional<Customer>>when(
				customerService.updateCustomerById(Mockito.anyLong(), Mockito.any(Customer.class)))
				.thenReturn(Optional.of(customerResponse));

		mockMvc.perform(put("/Customers/{id}", 1L).contentType(APPLICATION_JSON_)
				.content(convertObjectToJsonBytes(customerRequest))).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_)).andExpect(jsonPath("$.*", hasSize(7)))
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is("Kushagra Tiwari")))
				.andExpect(jsonPath("$.email", is("kushagra@gmail.com")))
				.andExpect(jsonPath("$.address", is("Bhopal, MP")))
				.andExpect(jsonPath("$.accountType", is("Current Account")))
				.andExpect(jsonPath("$.accountNo", is("DB1234567890")))
				.andExpect(jsonPath("$.accountBalance", is(1000.00)));

		ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
		verify(customerService, times(1)).updateCustomerById(Mockito.anyLong(), customerCaptor.capture());
		verifyNoMoreInteractions(customerService);
	}

	// Test for deleteCustomerById
	@Test
	public void deleteCustomerByIdTest() throws Exception {

		Mockito.when(customerService.deleteCustomerById(Mockito.anyLong()))
				.thenReturn(Optional.of(Object.class));

		mockMvc.perform(delete("/Customers/{id}", Mockito.anyLong())).andExpect(status().isOk());

		verify(customerService, times(1)).deleteCustomerById(Mockito.anyLong());
		verifyNoMoreInteractions(customerService);

	}

	// Testing ExceptionHandlers

}
