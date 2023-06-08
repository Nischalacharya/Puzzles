package model;

import java.io.File;

import javax.servlet.http.Part;

import resources.MyConstants;

public class UpdateUser {
	String userName, phone, address, email, imageUrlFormPart;

	public UpdateUser(String userName, String phone, String address, String email, Part part) {
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.imageUrlFormPart = getImageUrl(part);
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
