package com.example.banksystemclasses.model;

//import com.sun.istack.NotNull;
import com.example.banksystemclasses.cryptoconverter.Encryptor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Customers_info")
@SecondaryTables({
        @SecondaryTable(name = "customer", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customerInfo_id")),
        @SecondaryTable(name = "customerContact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customerInfo_id"))
})
@Getter@Setter
public class CustomerInfo
{
    @Id
    @GeneratedValue
    @Column(name="Customer_Id")
    private Long id;

    @NotNull(message="password must not be empty")
    @Column(name="Password")
    @Size(min=6)
    @Convert(converter = Encryptor.class)
    private String password;

    @NotNull(message="Aadhaar Number must not be empty")
    @Column(name="Aadhaar_No")
    @Pattern(regexp="^[0-9]{12}",message="Please enter Aadhaar Number in correct format.")
    @Convert(converter = Encryptor.class)
    private String aadhaarNo;

    @NotNull(message="PAN Number must not be empty")
    @Column(name="PAN_No")
    @Pattern(regexp="^[A-Za-z0-9]{10}",message="Please enter PAN Number in correct format.")
    @Convert(converter = Encryptor.class)
    private String panNo;

    @Embedded
    Customer customer;

    @Embedded
    CustomerContact customerContact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerContact getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(CustomerContact customerContact) {
		this.customerContact = customerContact;
	}

    
}
