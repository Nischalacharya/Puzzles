<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String userEmail = request.getParameter("userEmail");
%>
<%@page import="controller.stateManagement.SessionManage"%>
<%!SessionManage mySession = new SessionManage();%>
<%
String mainPath = request.getContextPath();
%>
<%
String user = (String) session.getAttribute("user");
%>
<%
String admin = (String) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<title>Puzzles | User Profile</title>
</head>
<c:if test="${not empty userEmail}">
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3307/puzzles" user="root" password="" />
	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="allUsers" dataSource="${dbConnection}">
		SELECT * FROM users where email = "${userEmail}"
	</sql:query>
	<body>
		<%
		String errorMessage = (String) request.getAttribute("errorMessage");
		%>
		<%
		if (errorMessage != null) {
		%>
		<div class="errorMessage"><%=errorMessage%></div>
		<%
		}
		%>

		<%
		String successMessage = (String) request.getAttribute("successMessage");
		%>
		<%
		if (errorMessage != null) {
		%>
		<div class="errorMessage"><%=successMessage%></div>
		<%
		}
		%>
		<nav id="navbar">
			<h1 class="logo">Puzzles</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
				<li><a
					href="${pageContext.request.contextPath}/pages/product.jsp">Product</a>
				</li>
				<li><a href="">About</a></li>
				<li><a
					href="${pageContext.request.contextPath}/pages/contact.html">Contact
						Us</a></li>
			</ul>
			<div class="logo-container">
				<!-- <span>Login</span> -->
				<a href="${pageContext.request.contextPath}/pages/admin.jsp"><i
					class="fa-solid fa-user"></i></a> <i class="fa-solid fa-cart-shopping"></i>
				<form
					action="
            <%if (!mySession.checkUser(user) || !mySession.checkIsAdmin(admin)) {

	out.print(mainPath);%>/pages/login.jsp <%} else {
	out.print(mainPath);%>/LogoutServlet<%}%>
            
            "
					method="post">
					<button type="submit">
						<i class="fa-solid fa-right-from-bracket"></i>
					</button>
				</form>
			</div>
		</nav>


		<c:forEach var="user" items="${allUsers.rows}">
			<section id="user-profile">
				<div class="profile-heading">
					<img src="http://localhost:8080/images/${user.image_url}"
						alt="User Profile" class="profile-img" />
					<div>
						<h1>${user.name}</h1>
						<p>
							<i class="fa-solid fa-envelope"></i>${user.email}</p>
						<p>
							<i class="fa-solid fa-phone"></i>${user.phone}</p>
					</div>
				</div>
				<h1>Account</h1>
				<form
					action="${pageContext.request.contextPath}/UpdateUserProfileServlet"
					class="user-profile-form" method="post"
					enctype="multipart/form-data">
					<div>
						<input type="hidden" id="name" name="id"
							placeholder="${user.user_id}" value="${user.user_id}" />
					</div>
					<div>
						<label for="name">Name</label> <input type="text" id="name"
							name="userName" placeholder="${user.name}" value="${user.name}" />
					</div>
					<div>
						<label for="email">Email</label> <input type="email" id="email"
							name="email" placeholder="${user.email}" value="${user.email}" />
					</div>
					<div>
						<label for="address">Address</label> <input type="text"
							id="address" name="address" placeholder="${user.address}"
							value="${user.address}" />
					</div>
					<div>
						<input type="hidden" id="password" name="password"
							placeholder="${user.password}" value="${user.password}" />
					</div>
					<div>
						<label for="phone">Phone</label> <input type="text" id="phone"
							name="phone" placeholder="${user.phone}" value="${user.phone}" />
					</div>
					<div>
						<label for="profile-image">Profile Image</label> <input
							type="file" name="image" id="profile-image"
							value="${user.image_url}" /> <span>${user.image_url}</span>
					</div>
					<button type="submit" class="btn-update">Update</button>
				</form>
			</section>
		</c:forEach>

		<footer id="footer">
			<p>&copy; 2023, Puzzles, All rights reserved.</p>
		</footer>
	</body>
</c:if>
</html>
