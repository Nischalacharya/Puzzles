package controller.servlets.addToCart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dbconnection.DbConnection;
import model.Cart;
import resources.MyConstants;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnection connection = new DbConnection();
		String prodId = request.getParameter("productId");
		int productId = Integer.parseInt(prodId);
		String userEmail = request.getParameter("userEmail");
		Cart cartModel = new Cart(productId, userEmail);
		int result = connection.addToCart(MyConstants.ADD_TO_CART, cartModel);
		if (result == 1) {
			request.setAttribute("successMessage", "Product Added to Cart!!");
			request.getRequestDispatcher("pages/product-detail.jsp").forward(request,  response);
		} else if (result == -1) {
			request.setAttribute("errorMessage", "Failed to Add Product to Cart!!");
			request.getRequestDispatcher("pages/product-detail.jsp").forward(request,  response);
		} else {
			request.getRequestDispatcher("pages/product-detail.jsp").forward(request, response);
		}
		doGet(request, response);
	}

}
