package controller.placeOrder;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dbconnection.DbConnection;
import model.Order;
import resources.MyConstants;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
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
		String userEmail = request.getParameter("userEmail");
		String productIdStr = request.getParameter("productId");
		int productId = Integer.parseInt(productIdStr);
		Date date = java.sql.Date.valueOf(LocalDate.now());
		Order orderModel = new Order(userEmail, productId,  date);
		
		int result = connection.placeOrder(MyConstants.ADD_TO_ORDER, orderModel);
		if (result == 1) {
			request.setAttribute("successMessage", "Your product was ordered successfully!!");
			request.getRequestDispatcher("pages/cart.jsp").forward(request,  response);
		} else if (result == -1) {
			request.setAttribute("errorMessage", "Failed to place order!!");
			request.getRequestDispatcher("pages/cart.jsp").forward(request,  response);
		} else {
			request.getRequestDispatcher("pages/cart.jsp").forward(request, response);
		}
		doGet(request, response);
	}

}
