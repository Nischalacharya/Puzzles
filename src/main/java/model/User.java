package model;

import java.io.File;

import javax.servlet.http.Part;

import resources.MyConstants;

public class User {
	String userName, phone, address, email, password, imageUrlFormPart;
	int id;

	

	public User() {
	}

	public User(String userName, String phone, String address, String email, String password,
			Part part) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		
		this.imageUrlFormPart = getImageUrl(part);
	}
	
	public User(int id, String userName, String phone, String address, String email, String password,
			Part part) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		
		this.imageUrlFormPart = getImageUrl(part);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getImageUrlFormPart() {
		return imageUrlFormPart;
	}

	public void setImageUrlFormPart(String imageUrlFormPart) {
		this.imageUrlFormPart = imageUrlFormPart;
	}

	private String getImageUrl(Part part) {
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFormPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFormPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFormPart == null || imageUrlFormPart.isEmpty()) {
			imageUrlFormPart = "download.png";
		}
		return imageUrlFormPart;
	}
}
