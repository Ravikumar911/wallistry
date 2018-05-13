package com.wallistry.model;

public class ProductItem {
	private long id;
	private String name;
	private long quantity;
	private long price;
	private String category;
	private long max_quantity;
	private String status;
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
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getMax_quantity() {
		return max_quantity;
	}
	public void setMax_quantity(long max_quantity) {
		this.max_quantity = max_quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ProductItem(long id, String name, long quantity, long price, String category, long max_quantity,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
		this.max_quantity = max_quantity;
		this.status = status;
	}
	public ProductItem() {
	}

}
