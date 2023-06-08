package controller.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import resources.MyConstants;
import model.Product;

@WebServlet(asyncSupported = true, urlPatterns = {"/ProductAdd"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"); 
		String description = request.getParameter("description"); 
		String category = request.getParameter("category"); 
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		float rating = Float.parseFloat(request.getParameter("rating"));
		String brand = request.getParameter("brand"); 
		Part image = request.getPart("image"); 
		Product productModel = new Product(name, description, category, price, stock, rating, brand, image);
		
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
	    String fileName = productModel.getImage();
	    if(!fileName.isEmpty() && fileName != null)
    		image.write(savePath + fileName);
		
		DbConnection con = new DbConnection();
		int result = con.addProduct(MyConstants.PRODUCT_ADD, productModel);
		
		if (result == 1) {
			request.setAttribute("registerMessage", "Successfully Added");
			request.getRequestDispatcher("/pages/admin.jsp").forward(request, response);
		} else if (result == -1) {
			request.setAttribute("registerMessage", "User Already Exists");
			request.getRequestDispatcher("/pages/addProduct.jsp").forward(request, response);
		}else {
			request.setAttribute("registerMessage", "Error");
			request.getRequestDispatcher("/pages/addProduct.jsp").forward(request, response);
		}
	}

}
