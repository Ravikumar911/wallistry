package com.wallistry.model;

public class Subscription {
	private long id;
	private String email;
	private String date;

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Subscription(long id, String email, String date) {
		super();
		this.id = id;
		this.email = email;
		this.date = date;
	}

	public Subscription() {
	}

}
