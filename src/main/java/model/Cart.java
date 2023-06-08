package model;

public class Cart {
		
	public Cart(int productId, String userEmail) {
		super();
		this.productId = productId;
		this.userEmail = userEmail;
	}
	
	private int productId;
	private String userEmail;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
		
}