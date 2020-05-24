package com.example.Customer.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.Customer.Model.Customer;
import com.example.Customer.Repository.CustomerRepository;

@Service
public class CustomerService {


	@Autowired
	CustomerRepository customerRepository;

	
	
	//get all customers
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	//get customer by id
	public Optional<Customer> getCustomerById(Long customerId) {
		
		return customerRepository.findById(customerId);
	}
	
	//create a new customer
	public Customer createNewCustomer(Customer customerRequest) {
		return customerRepository.save(customerRequest);
	}
	//update a customer
	public Optional<Customer> updateCustomerById(Long customerId, Customer customerRequest) {
		return customerRepository.findById(customerId)
				.map(customer->{
					customer.setName(customerRequest.getName());
					customer.setEmail(customerRequest.getEmail());
					customer.setAddress(customerRequest.getAddress());
					customer.setAccountType(customerRequest.getAccountType());
					customer.setAccountNo(customerRequest.getAccountNo());
					customer.setAccountBalance(customerRequest.getAccountBalance());
					return customerRepository.save(customer);
				});
	}
	//delete a customer	
	public Object deleteCustomerById(@PathVariable Long id){
		return customerRepository.findById(id)
				.map(customer->{
					customerRepository.delete(customer);
					return customer;
				});
	}
}
