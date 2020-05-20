package com.example.banksystemclasses.model;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.Date;

@Embeddable
@Getter @Setter

public class Customer
{
    @NotNull(message="First_Name must not be empty")
    @Column(name="First_Name", table = "customer")
    @Pattern(regexp="^[A-Za-z]+",message="Please enter First Name in correct format.")
    private String firstName;

    @NotNull(message="Last_Name must not be empty")
    @Column(name="Last_Name", table = "customer")
    @Pattern(regexp="^[A-Za-z]+",message="Please enter Last Name in correct format.")
    private String lastName;

    @NotNull(message="Date should not be null.")
    @Column(name="DOB", table = "customer")
    @Temporal(TemporalType.DATE)
    private Date dob;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}


}
