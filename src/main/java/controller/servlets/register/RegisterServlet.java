package controller.servlets.register;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import model.User;
import resources.MyConstants;

/**
 * Servlet implementation class RegisterServlet
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/RegisterServlet"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2,//2mb
	maxFileSize = 1024*1024*10, //10MB
	maxRequestSize = 1024*1024*50
		) 
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("Hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnection connection = new DbConnection();
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
		Part imagePart = request.getPart("image");
		User userModel = new User(userName, phone, address, email, password, imagePart);
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		String fileName = userModel.getImageUrlFormPart();
		if(!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath+fileName);
	
		}
		int result = connection.registerUser(MyConstants.REGISTER_USER, userModel);
		if(result == 1) {
			request.setAttribute("registerMessage", "user registered");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}else if(result == -1) {
			request.setAttribute("registerMessage", "user already registered");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("pages/register.jsp").forward(request, response);
		}
		
		
		
		doGet(request, response);
	}

}
