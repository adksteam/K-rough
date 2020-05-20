package com.example.banksystemclasses.service;

import com.example.banksystemclasses.exception.ResourceNotFoundException;
import com.example.banksystemclasses.model.*;
import com.example.banksystemclasses.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerInfoRepository customerRepository;

    //get all customers
    public List<CustomerInfo> getAllCustomers(){
        return customerRepository.findAll();
    }

    //get customer by id
    public Optional<CustomerInfo> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    //create a new customer
    public CustomerInfo createNewCustomer(CustomerInfo customer) {
        return customerRepository.save(customer);
    }

    //update a customer all details
    public CustomerInfo updateCustomer(CustomerInfo customer){ return customerRepository.save(customer); }

    //update mobileNo of customer
    public CustomerContact updateCustomerMobile(CustomerContact customerContact, Long id){
        CustomerInfo customerInfo = getCustomerById(id).orElseThrow(()->new ResourceNotFoundException("Please Login First."));
        CustomerContact originalCustomerContact = customerInfo.getCustomerContact();
        originalCustomerContact.setMobileNo(customerContact.getMobileNo());
        customerInfo.setCustomerContact(originalCustomerContact);
        updateCustomer(customerInfo);
        return originalCustomerContact;
    }

    //update customer email
    public CustomerContact updateCustomerEmail(CustomerContact customerContact, Long id){
        CustomerInfo customerInfo = getCustomerById(id).orElseThrow(()->new ResourceNotFoundException("Please Login First."));;
        CustomerContact originalCustomerContact = customerInfo.getCustomerContact();
        originalCustomerContact.setEmail(customerContact.getEmail());
        customerInfo.setCustomerContact(originalCustomerContact);
        updateCustomer(customerInfo);
        return originalCustomerContact;
    }

    public CustomerContact updateCustomerAddress(CustomerContact customerContact, Long id){
        CustomerInfo customerInfo = getCustomerById(id).orElseThrow(()->new ResourceNotFoundException("Please Login First."));;
        CustomerContact originalCustomerContact = customerInfo.getCustomerContact();
        originalCustomerContact.setAddress(customerContact.getAddress());
        originalCustomerContact.setCity(customerContact.getCity());
        originalCustomerContact.setZipCode(customerContact.getZipCode());
        originalCustomerContact.setState(customerContact.getState());
        originalCustomerContact.setCountry(customerContact.getCountry());

        customerInfo.setCustomerContact(originalCustomerContact);
        updateCustomer(customerInfo);
        return originalCustomerContact;
    }

    //delete a customer details
    public String deleteCustomer(Long id){
        CustomerInfo customerInfo = getCustomerById(id).orElseThrow(()->new ResourceNotFoundException("Please Login First."));;
        customerRepository.delete(customerInfo);
        return "Customer deleted";
    }

}
