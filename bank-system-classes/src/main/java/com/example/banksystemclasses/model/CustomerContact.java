package com.example.banksystemclasses.model;


import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@Getter @Setter
public class CustomerContact {

    @NotNull(message="Address Line-1 can not be empty.")
    @Column(name="Address", table = "customerContact")
    @Pattern(regexp="^[A-Za-z]+",message="Please enter Address in correct format.")
    private String address;


    @NotNull(message="City cannot be null.")
    @Column(name="City", table = "customerContact")
    @Pattern(regexp="^[A-Za-z]+",message="Please enter city in correct format.")
    private String city;

    @NotNull(message="ZIP Code can not be empty.")
    @Column(name="ZIP_Code", table = "customerContact")
    @Pattern(regexp="^[0-9]{6}",message="Please enter 6-Digit ZIP Code.")
    private String zipCode;

    @NotNull(message="State can not be empty.")
    @Column(name="State", table = "customerContact")
    @Pattern(regexp="^[A-Za-z]+",message="Please enter State in correct format.")
    private String state;

    @NotNull(message="Country can not be empty.")
    @Column(name="Country", table = "customerContact")
    @Pattern(regexp="^[A-Za-z]+",message="Please enter Country in correct format.")
    private String country;

    @NotNull(message="Mobile_No must not be null")
    @Column(name="Mobile_No", table = "customerContact")
    @Pattern(regexp="^[0-9]{10}",message="Please enter 10-Digit Mobile No.")
    private String mobileNo;

    @NotNull(message="Email should not be missing")
    @Email(message="Email format should be like customer@db.com")
    @Column(name="Email", table = "customerContact")
    private String email;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
