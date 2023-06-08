<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String mainPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
	<title>Puzzles | Login</title>
</head>

<body>
	<div id="login">
	<%String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if(errorMessage != null) { %>
	<div class="errorMessage"><%= errorMessage %></div>
	<% } %>
		<form action="${pageContext.request.contextPath}/LoginServlet" method="post" class="form">
			<h1>Login</h1>
			<div class="input-group">
				<div>
					<label for="username">Email</label>
					<input type="text" name="email" id="email" placeholder="Enter your email" />
				</div>
				<div>
					<label for="password">Password</label>
					<input type="password" name="password" id="password" placeholder="Enter your password" />
				</div>
			</div>
			<button type="submit" class="btn">Login</button>
			<p>Don't have an account? <span><a href="${pageContext.request.contextPath}/pages/register.jsp">Register</a></span></p>
		</form>
	</div>
</body>

</html>