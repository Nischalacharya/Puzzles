<%@page import="resources.MyConstants"%>
<%@page import="controller.dbconnection.DbConnection"%>
<%@page import="controller.stateManagement.SessionManage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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

<%@ page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%!Connection con = null;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="../css/style.css" />
<title>Puzzles | Cart</title>
</head>
<body>
	<c:set var="userEmail" value="${user }" />
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3307/puzzles" user="root" password="" />
	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="cartProducts" dataSource="${dbConnection}">
		SELECT cart.product_id, products.name, products.price FROM cart, products 
		WHERE cart.product_id = products.product_id
		AND user = "${userEmail}"
	</sql:query>

	<nav id="navbar">
		<h1 class="logo">Puzzles</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
			<li><a
				href="${pageContext.request.contextPath}/pages/product.jsp">Product</a></li>
			<li><a href="${pageContext.request.contextPath}/pages/admin.jsp">Admin</a></li>
			<li><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
		</ul>

		<div class="logo-container">
			<a href="${pageContext.request.contextPath}/pages/userProfile.jsp"><i
				class="fa-solid fa-user"></i></a> <a
				href="${pageContext.request.contextPath}/pages/cart.jsp"><i
				class="fa-solid fa-cart-shopping"></i></a> <a
				href="${pageContext.request.contextPath}/pages/userProfile.jsp"><i
				class="fa-solid fa-right-from-bracket"></i></a>
		</div>
	</nav>

	<section id="product-list">
		<div class="list-container">
			<div class="list-heading">
				<h1>Cart Items</h1>
			</div>
			<table class="list-table">
				<tr>
					<th>Product</th>
					<th>Price</th>
					<th>Action</th>
				</tr>

				<c:forEach var="product" items="${cartProducts.rows}">
					<tr>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td class="btn-container">
							<form action="${pageContext.request.contextPath}/PlaceOrder"
								method="post">
								<input type="hidden" name="userEmail" value="${userEmail}" /> <input
									type="hidden" name="productId" value="${product.product_id}" />
								<button class="edit-button">Order</button>
							</form>
							<form method="post">
								<input type="hidden" name="deleteId"
									value="${product.product_id}" />
								<button class="delete-button">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%
			DbConnection con = new DbConnection();
			String successMessage = (String) request.getAttribute("successMessage");
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (successMessage != null) {
				int orderedProcuctId = Integer.parseInt(request.getParameter("productId"));
				con.deleteCartItems(MyConstants.CART_ITEM_DELETE, orderedProcuctId);
			%>
			<p class="success-msg">${successMessage}</p>
			<%
			} else if (errorMessage != null) {
			%>
			<p class="error-msg">${errorMessage}</p>
			<%
			}
			%>
		</div>
	</section>
	<%
	if (request.getParameter("deleteId") != null) {
		int id = Integer.parseInt(request.getParameter("deleteId"));
		con.deleteCartItems(MyConstants.CART_ITEM_DELETE, id);
	}
	%>

	<footer id="footer">
		<p>
			&copy; <span id="year"></span>, Puzzles, All rights reserved.
		</p>
	</footer>

	<script src="${pageContext.request.contextPath}/js/main.js">
		
	</script>
</body>
</html>