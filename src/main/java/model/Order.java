package model;

import java.sql.Date;

public class Order {
	private String user;
	private int productId;
	private java.sql.Date date;
	
	public Order(String user, int productId, Date date) {
		super();
		this.user = user;
		this.productId = productId;
		this.date = date;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}