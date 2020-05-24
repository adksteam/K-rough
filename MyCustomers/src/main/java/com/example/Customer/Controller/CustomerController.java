package com.example.Customer.Controller;

//Module for controlling HttpRequests and Sending HttpResposes

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Customer.Exception.ResourceNotFoundException;
import com.example.Customer.Model.Customer;
import com.example.Customer.Service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// Get all Customers
	// If No Customers then raise Exception
	@GetMapping("/Customers")
	@ResponseBody
	public List<Customer> getAllCustomers() {
		List<Customer> Customers = customerService.getAllCustomers();
		if (Customers.isEmpty()) {
			throw new ResourceNotFoundException("No Customer Found.");
		}
		return Customers;
	}

	// Get Customers by Id
	// If Customer with required Id is not found then raise
	// ResourceNotFoundException
	@GetMapping("/Customers/{id}")
	@ResponseBody
	public Customer getCustomerById(@PathVariable("id") Long id) {
		
		return customerService.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not found with id:" + id));
		
	}

	// Create a new Customer
	// If Validation Constraints of any field is broken then raise
	// MethodArgumentInvalidException
	@PostMapping("/Customers")
	@ResponseBody
	public Customer createCustomer(@Valid @RequestBody Customer customerRequest) {
		return customerService.createNewCustomer(customerRequest);

	}

	// Update a new customer by Id
	// If Customer with required Id is not found then ResourceNotFoundException
	// If Validation Constraints of any field is broken then raise
	// MethodArgumentInvalidException
	@PutMapping("/Customers/{id}")
	public Customer updateCustomerById(@PathVariable("id") Long id, @Valid @RequestBody Customer customerRequest) {
		return customerService.updateCustomerById(id, customerRequest)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not found with id:" + id));
	}

	// Delete a customer by id
	// If Customer with required Id is not found then ResourceNotFoundException
	@DeleteMapping("/Customers/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Long id) {

		try {
			customerService.deleteCustomerById(id);
		} catch (Exception ex) {
			throw new ResourceNotFoundException("Customer Not found with id:" + id);
		}

		return ResponseEntity.ok().build();

	}
}
