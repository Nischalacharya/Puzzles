package controller.servlets.update;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import model.UpdateUser;
import model.User;
import resources.MyConstants;

/**
 * Servlet implementation class UpdateUserProfileServlet
 */
@WebServlet(asyncSupported= true, urlPatterns={"/UpdateUserProfileServlet"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2,//2mb
maxFileSize = 1024*1024*10, //10MB
maxRequestSize = 1024*1024*50
	) 
public class UpdateUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserProfileServlet() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Part imagePart = request.getPart("image");
		User user = new User(id,userName, phone, address, email ,password , imagePart);
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		String fileName = user.getImageUrlFormPart();
		if(!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath+fileName);
	
		}
		boolean result = connection.updateUser(MyConstants.USER_UPDATE, user, id);
		
		if(result == true) {
			request.setAttribute("successMessage", "user updated");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.include(request, response);
		}else {
			request.setAttribute("errorMessage", "error updating user");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/userProfile.jsp");
			dispatcher.include(request, response);
		}
		
	}

}
