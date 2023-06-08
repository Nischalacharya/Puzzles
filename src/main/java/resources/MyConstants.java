package resources;

import java.io.File;

public class MyConstants {
	// Database configuration
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3307/puzzles";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "";

	// Start region: query
	public static final String CHECK_LOGIN_INFO = "SELECT email FROM users where email = ?";
	public static final String GET_ALL_USERS = "SELECT * FROM users";
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users" + "WHERE email=?";
	public static final String REGISTER_USER = "INSERT INTO users(name, email, password, address, phone, image_url) values(?,?,?,?,?,?)";
	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;
	public static final String LOGIN_CHECK = "SELECT email, password from users where email = ? and password = ?";

	// END Region: Query

	// Start Region: Insert Query
	public static final String PRODUCT_ADD = "Insert into products(name, description, category, price, stock, rating, brand, image)values(?, ?, ?, ?, ?, ?, ?, ?)";
	// End Region: Insert Query

	// Start Region: Select By ID Query
	public static final String SELECT_PRODUCT_BY_ID = "select product_id, name, description, category, price, stock, rating, brand, image from products where product_id =?";
	// End Region: Select By ID Query

	// Start Region: Select All Query
	public static final String SELECT_ALL_PRODUCTS = "select * from products";
	// End Region: Select All Query

	// Start Region: Update Query
	public static final String PRODUCT_UPDATE = "UPDATE products SET product_id=?, name=?, description=?, category=?, price=?, stock=?, rating=?, brand=?, image=? WHERE product_id=?";
	// End Region: Update Query

	// Start Region: Delete Query
	public static final String PRODUCT_DELETE = "DELETE FROM products WHERE product_id=?";
	// End Region: Delete Query

	// Start Region: Delete Query
	public static final String USER_UPDATE = "UPDATE users SET user_id=?, name=?, email=?, password =?, address=?, phone=?, image_url=? WHERE user_id=?";

	// Start Region: Delete Query
		public static final String USER_DELETE = "DELETE FROM users WHERE email=?";
		// End Region: Delete Query
	
	// End Region: Delete Query

	// Start Region: Delete Query
	public static final String CART_ITEM_DELETE = "DELETE FROM cart WHERE product_id=?";
	// End Region: Delete Query

	public static final String ADD_TO_CART = "INSERT INTO cart(product_id, user) values(?,?)";

	public static final String ADD_TO_ORDER = "INSERT INTO orders(user, product_id, date) values(?,?,?)";

}
