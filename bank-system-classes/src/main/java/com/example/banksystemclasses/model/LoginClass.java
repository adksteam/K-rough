package com.example.banksystemclasses.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginClass
{
    private Long id;
    private String password;
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
    
    
}
