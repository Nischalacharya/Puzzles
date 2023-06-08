package controller.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DbConnection;
import resources.MyConstants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ADMIN_EMAIL = "admin@admin.com";
    private final String  ADMIN_PASSWORD = "admin";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		DbConnection connection = new DbConnection();
		Boolean isUserRegistered = connection.isUserRegistered(MyConstants.CHECK_LOGIN_INFO, userEmail, password);
		if(ADMIN_EMAIL.equals(userEmail)&& ADMIN_PASSWORD.equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", ADMIN_EMAIL);
			//
			session.setMaxInactiveInterval(30*60);
			
			//cookie
			Cookie AdminEmail = new Cookie("admin", ADMIN_EMAIL);
			AdminEmail.setMaxAge(30*60);
			response.addCookie(AdminEmail);
			response.sendRedirect(request.getContextPath()+"/pages/admin.jsp");
		}
		else if(isUserRegistered != null && isUserRegistered) {
			HttpSession session = request.getSession();
			session.setAttribute("user", userEmail);
			session.setMaxInactiveInterval(30*60);
			 // cookie
			Cookie  user = new Cookie("user", userEmail);
			user.setMaxAge(30*60);
			response.addCookie(user);
			response.sendRedirect(request.getContextPath()+"/pages/product.jsp");
			
			
		}
		else {
			//set error messages
			request.setAttribute("errorMessage", "invalid username or password, please try again");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
			dispatcher.include(request, response);
		}
		doGet(request, response);
	}

}
