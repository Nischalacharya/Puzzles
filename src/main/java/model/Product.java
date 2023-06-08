package model;

import java.io.File;

import javax.servlet.http.Part;

import resources.MyConstants;

public class Product {
	int id;
	String name; 
	String description; 
	String category; 
	int price; 
	int stock;
	float rating;
	String brand;	
	String image;
	
	public Product(int id, String name, String description, String category, int price, int stock, float rating, String brand, Part part) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.rating = rating;
		this.brand = brand;
		this.image = getImage(part);
	}
		
	public Product(String name, String description, String category, int price, int stock, float rating, String brand, Part part) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.rating = rating;
		this.brand = brand;
		this.image = getImage(part);
	}
	


	// Getters	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public float getRating() {
		return rating;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getImage() {
		return image;
	}
	
	private String getImage(Part part) {
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String image = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				image = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if(image == null || image.isEmpty()) {
			image= "no-image.jpg";
		}
		return image;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setImageUrlFromPart(Part part) {
		this.image = getImage(part);
	}
}
