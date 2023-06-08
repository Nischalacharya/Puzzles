package controller.filters.login;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class RequestLoggingFilter implements Filter{
private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = request.getParameter(name);
			this.context.log(req.getRemoteAddr()+"::Request params::{"+name+"="+value+ "}");
		}
		//pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	
	
	@Override
	public void destroy() {}

}
