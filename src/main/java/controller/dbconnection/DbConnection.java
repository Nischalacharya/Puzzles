package controller.dbconnection;

import java.sql.Connection;

import model.Cart;
import model.Order;
import model.Product;
import model.UpdateUser;
import model.User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import resources.MyConstants;

public class DbConnection {
	public Connection getConnection() {
		try {
			Class.forName(MyConstants.DRIVER_NAME);
			Connection connection = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USERNAME,
					MyConstants.DB_PASSWORD);
			return connection;

		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

//	Start region Read operation
	public List<User> getAllData(String query) {
		List<User> dataList = new ArrayList<User>();
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				Statement stmt = dbConnection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					User data = new User();
					data.setUserName(rs.getString("name"));
					data.setEmail(rs.getString("email"));
					data.setAddress(rs.getString("address"));
					data.setPhone(rs.getString("phone"));
					data.setImageUrlFormPart(rs.getString("image_url"));
					dataList.add(data);
				}
				rs.close();
				stmt.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}

	public ResultSet selectAllQuery(String query) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				ResultSet result = statement.executeQuery();
				return result;

			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		} else {
			return null;
		}
	}

	public Boolean isUserAlreadyRegistered(String email) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_LOGIN_INFO);
				statement.setString(1, email);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					return true;
				} else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}

	}

	public Boolean isUserRegistered(String query, String email, String password) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.LOGIN_CHECK);
				statement.setString(1, email);
				statement.setString(2, password);
				ResultSet result = statement.executeQuery();
				if (result.next())
					return true;
				else
					return false;

			} catch (SQLException e) {
				// TODO: handle exception
				return null;

			}

		} else {
			return null;

		}
	}

	// Start region Create operation
	public int registerUser(String query, User userModel) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				if (isUserAlreadyRegistered(userModel.getEmail()))
					return -1;

				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, userModel.getUserName());
				statement.setString(2, userModel.getEmail());
				statement.setString(3, userModel.getPassword());
				statement.setString(4, userModel.getAddress());
				statement.setString(5, userModel.getPhone());
				statement.setString(6, userModel.getImageUrlFormPart());

				int result = statement.executeUpdate();
				if (result >= 0)
					return 1;
				else
					return 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return -2;
			}
		} else {
			return -3;
		}
	}
	// End region Create operation

	// Start region add product
	public int addProduct(String query, Product productModel) {
		Connection dbConnection = getConnection();

		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, productModel.getName());
				statement.setString(2, productModel.getDescription());
				statement.setString(3, productModel.getCategory());
				statement.setInt(4, productModel.getPrice());
				statement.setInt(5, productModel.getStock());
				statement.setFloat(6, productModel.getRating());
				statement.setString(7, productModel.getBrand());
				statement.setString(8, productModel.getImage());

				int result = statement.executeUpdate();
				if (result > 0) {
					return 1;
				} else {
					return 0;
				}
			} catch (Exception ex) {
				return -2;
			}
		} else {
			return -3;
		}
	}

	// select user by id
	public Product selectProduct(String query, int id) {
		Product product = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				float rating = rs.getFloat("rating");
				String brand = rs.getString("brand");
				String image = rs.getString("image");

				product = new Product(id, name, description, category, price, stock, rating, brand, null);
			}
		} catch (SQLException e) {
			return null;
		}
		return product;
	}

	// select all products
	public List<Product> selectAllProducts(String query) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Product> products = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				float rating = rs.getFloat("rating");
				String brand = rs.getString("brand");
				String image = rs.getString("image");

				products.add(new Product(id, name, description, category, price, stock, rating, brand, null));

			}
		} catch (SQLException e) {
			return null;
		}
		return products;
	}

	// Start region Update operation
	public Boolean updateProduct(String query, Product product,int id) {
		Connection connection = getConnection();
		boolean updated = false;
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, product.getId());
				statement.setString(2, product.getName());
				statement.setString(3, product.getDescription());
				statement.setString(4, product.getCategory());
				statement.setInt(5, product.getPrice());
				statement.setInt(6, product.getStock());
				statement.setFloat(7, product.getRating());
				statement.setString(8, product.getBrand());
				statement.setString(9, product.getImage());
				statement.setInt(10, id);
				statement.executeUpdate();
				updated = true;
			}catch(Exception e) {
				e.getMessage();
				System.out.println(e.getMessage());
			}
		}
		return updated;
	}
	// End region Update operation


	// Start region Delete operation
	public Boolean deleteProduct(String query, int id) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, id);
				int result = statement.executeUpdate();
				if (result >= 0)
					return true;
				else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	// End region Delete operation
	
	// Start region Delete operation
	public Boolean deleteUser(String query, String email) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, email);
				int result = statement.executeUpdate();
				if (result >= 0)
					return true;
				else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	// End region Delete operation

	// Start region update user
//		public static final String USER_UPDATE = "UPDATE users SET name=?, email=?, password=?, address=?, phone=?, image_url=? WHERE email=?";

	public Boolean updateUser(String query, User user, int id) {
		boolean updated = false;
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, user.getId());
				statement.setString(2, user.getUserName());
				statement.setString(3, user.getEmail());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getAddress());
				statement.setString(6, user.getPhone());
				statement.setString(7, user.getImageUrlFormPart());
				statement.setInt(8, id);
				statement.executeUpdate();
				updated = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return updated;
	}

	// End user update user
	// Start region Add to cart
	public int addToCart(String query, Cart cartModel) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {

				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, cartModel.getProductId());
				statement.setString(2, cartModel.getUserEmail());

				int result = statement.executeUpdate();
				if (result >= 0)
					return 1;
				else
					return 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return -2;
			}
		} else {
			return -3;
		}
	}
	// End region Add to cart

//		Start region Delete operation
	public Boolean deleteCartItems(String query, int id) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setInt(1, id);
				int result = statement.executeUpdate();
				if (result >= 0)
					return true;
				else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	// End region Delete operation
	
	

	// Start region Place Order
	public int placeOrder(String query, Order orderModel) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {

				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, orderModel.getUser());
				statement.setInt(2, orderModel.getProductId());
				statement.setDate(3, orderModel.getDate());

				int result = statement.executeUpdate();
				if (result >= 0)
					return 1;
				else
					return 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return -2;
			}
		} else {
			return -3;
		}
	}
	// End region APlace order
}
