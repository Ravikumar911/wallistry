package com.wallistry.model;

public class Customer {
	private long id;
	private String name;
	private String email;
	private String phone_number;
	private String address;
	private long pincode;
	private String state;
	private String date;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Customer(long id, String name, String email, String phone_number, String address, long pincode, String state,
			String date) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;
		this.pincode = pincode;
		this.state = state;
		this.date = date;
	}
	public Customer() {
	}
	
}
