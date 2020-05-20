package com.example.banksystemclasses.controller;

import com.example.banksystemclasses.exception.ResourceNotFoundException;
import com.example.banksystemclasses.model.*;
import com.example.banksystemclasses.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private Long loginId = Long.valueOf(-1);
    private String loginPassword = "";

    //Get all Customers
    @GetMapping("/Customers")
    public List<CustomerInfo> getAllCustomers(){
        List<CustomerInfo> customers=customerService.getAllCustomers();
       if(customers.isEmpty()){
            throw new ResourceNotFoundException("No Customer Found.");
        }
        return customers;
    }

    //Get Customers by Id
    @GetMapping("/Customers/{id}")
    public CustomerInfo getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id).orElseThrow(()->new ResourceNotFoundException("No Customer found for Id: "+id));
    }

    //Create a new Customer
    @PostMapping("/Customers")
    public CustomerInfo createCustomer(@RequestBody CustomerInfo customer) {
        return customerService.createNewCustomer(customer);

    }

    @PutMapping("/Customers")
    public CustomerInfo updateCustomer(@RequestBody CustomerInfo customer){
        if(customer.getId() == loginId)
            return customerService.updateCustomer(customer);
        else
        	throw new ResourceNotFoundException("Please Login First.");
    }

    @PatchMapping("/Customers/updateMobile/{id}")
    public CustomerContact updateCustomerMobile(@RequestBody CustomerContact customerContact, @PathVariable("id") Long id){
        if(loginId == id)
            return customerService.updateCustomerMobile(customerContact, id);
        else
        	throw new ResourceNotFoundException("Please Login First.");
    }

    @PatchMapping("/Customers/updateEmail/{id}")
    public CustomerContact updateCustomerEmail(@RequestBody CustomerContact customerContact, @PathVariable("id") Long id){
        if(loginId == id)
            return customerService.updateCustomerEmail(customerContact, id);
        else
        	throw new ResourceNotFoundException("Please Login First.");
    }

    @PatchMapping("/Customers/updateAddress/{id}")
    public CustomerContact updateCustomerAddress(@RequestBody CustomerContact customerContact, @PathVariable("id") Long id){
        if(loginId == id)
            return customerService.updateCustomerAddress(customerContact, id);
        else
        	throw new ResourceNotFoundException("Please Login First.");
    }

    @DeleteMapping("/Customers/{id}")
    public String deleteCustomer(@PathVariable("id") Long id){
        if(loginId == id)
            return customerService.deleteCustomer(id);
        else
        	throw new ResourceNotFoundException("Please Login First.");
            
    }

    @PostMapping("/Customers/login")
    private CustomerInfo loginCustomer(@RequestBody LoginClass loginClass){
        Long id = loginClass.getId();
        return customerService.getCustomerById(id)
        		.map(customerInfo->{
        			String enteredPassword = loginClass.getPassword();
        	        if(enteredPassword.equals(customerInfo.getPassword())){
        	            loginId = id;
        	            loginPassword = enteredPassword;
        	            return customerInfo;
        	        }
        	        else
        	            return null;

        		}
        				)
        		.orElseThrow(()->new ResourceNotFoundException("Incorrect Credentials."));
        
            }
}
