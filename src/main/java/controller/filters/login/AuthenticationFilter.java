package controller.filters.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		// get the servlet context
		this.context = filterConfig.getServletContext();
		// log that the filter has been initialized
		this.context.log("Auth filter Initialized");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast the request and response objects to HTTP servlet request and response objects
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// check if the request is for login or logout pages or servlets
		String uri = req.getRequestURI();
		boolean isLoginJsp = uri.endsWith("login.jsp");
		boolean isLoginServlet = uri.endsWith("LoginServlet");
		boolean isLogoutServlet = uri.endsWith("LogoutServlet");
		boolean isRegisterJsp = uri.endsWith("register.jsp");
		boolean isRegisterServlet = uri.endsWith("RegisterServlet");
		boolean homePage = uri.endsWith("index.jsp");
		boolean adminPage = uri.endsWith("admin.jsp");
		boolean addProductPage = uri.endsWith("addProduct.jsp");
		boolean addProductServlet = uri.endsWith("ProductAdd");
		boolean productDetailsPage = uri.endsWith("product-detail.jsp");
//		boolean editProductPage = uri.endsWith("updateProduct.jsp");
		boolean isEditProductPage = uri.endsWith("updateProduct.jsp");
		this.context.log("requested resource:" + uri);
		
		// get the session object and check if the user is logged in or admin is logged in
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("user") != null;
		boolean adminLoggedIn = session != null && session.getAttribute("admin") != null;
		
		// if the user is not logged in and not requesting login, logout, register or home page or css, redirect to login page
		if(!loggedIn && !adminLoggedIn && !(isLoginJsp || isLoginServlet|| isRegisterJsp ||isRegisterServlet || homePage) && !uri.contains("css")) {
			// redirect to the login page
			res.sendRedirect(req.getContextPath()+"/pages/login.jsp");
		}
		// if both user and admin are logged in and not requesting login, logout, addProduct or product page, redirect to product page
		// if both user and admin are logged in and not requesting login, logout, addProduct, product, or edit product page, redirect to product page
		else if ((loggedIn && adminLoggedIn) && !isLoginJsp && !isLogoutServlet && !addProductPage && !productDetailsPage && !isEditProductPage) {
		    res.sendRedirect(req.getContextPath() + "/pages/product.jsp");
		}
//		else if(!adminLoggedIn) {
//			res.sendRedirect(req.getContextPath()+"/pages/product.jsp");
//		}
		// otherwise, pass the request along the filter chain
		else {
			chain.doFilter(request, response);
		}	
	}
	
	
	@Override
	public void destroy() {}

}
