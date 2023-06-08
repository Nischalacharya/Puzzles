<%@page import="resources.MyConstants"%>
<%@page import="controller.dbconnection.DbConnection"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="controller.stateManagement.SessionManage"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%!SessionManage mySession = new SessionManage();%>
<%
String mainPath = request.getContextPath();
%>
<%
String user = (String) session.getAttribute("user");
out.println(user);
%>
<%
String admin = (String) session.getAttribute("admin");
out.println(admin);
%>


<%!Connection con = null;%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<title>Puzzles | Admin</title>
</head>
<body>
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3307/puzzles" user="root" password="" />
	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="allUsers" dataSource="${dbConnection}">
		SELECT * FROM users
	</sql:query>
	<sql:query var="allProducts" dataSource="${dbConnection}">
		SELECT product_id, name, category, price, brand, stock FROM products ORDER BY product_id;
	</sql:query>
	<sql:query var="allOrders" dataSource="${dbConnection}">
		SELECT orders.order_id, products.name, products.price, orders.user 
		FROM orders, products
		WHERE orders.product_id = products.product_id
	</sql:query>
	
	<nav id="navbar">
        <h1 class="logo">Puzzles</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/product.jsp">Product</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/admin.jsp">Admin</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
        </ul>

        <div class="logo-container">
            <a href="${pageContext.request.contextPath}/pages/userProfile.jsp"><i class="fa-solid fa-user"></i></a>
            <a href="${pageContext.request.contextPath}/pages/cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
            <a href="${pageContext.request.contextPath}/pages/userProfile.jsp"><i class="fa-solid fa-right-from-bracket"></i></a>
        </div>
    </nav>

	<section id="product-list">
		<div class="list-container">
			<div class="list-heading">
				<h1>Product List</h1>
				<a href="./addProduct.jsp" class="btn-add"><i
					class="fa-solid fa-plus"></i>Add product</a>
			</div>
			<table class="list-table">
				<tr>
					<th>Name</th>
					<th>Category</th>
					<th>Brand</th>
					<th>Price</th>
					<th>Stock</th>
					<th>Action</th>
				</tr>
				<c:forEach var="product" items="${allProducts.rows}">
					<tr>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.brand}</td>
						<td>${product.price}</td>
						<td>${product.stock}</td>
						<td class="btn-container">
							<form action="${pageContext.request.contextPath}/pages/updateProduct.jsp" method="post">

								<input type="hidden" name="products" value="${product.product_id}" />
								
								<button type="submit" class="edit-button"><i class="fa-solid fa-plus"></i>Edit
								</button>
								<!--
																<input type="hidden" name="updateId" value="${product.id}" />
								<button type="submit" class="btn-add"> <i class="fa-solid fa-plus"></i>Edit
								</button> 
								<input type="hidden" name="updateId" value="${product.name}" />
								<button class="edit-button">Edit</button>
								 -->
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
		</div>
	</section>

	<section id="user-list">
		<div class="list-container">
			<div class="list-heading">
				<h1>User List</h1>
				<a href="${pageContext.request.contextPath}/pages/register.jsp"
					class="btn-add"><i class="fa-solid fa-plus"></i>Add user</a>
			</div>
			<table class="list-table">
				<tr>
					<th>User Name</th>
					<th>Email</th>
					<th>Phone Number</th>
					<th>Address</th>
					<th>Action</th>

				</tr>


				<c:forEach var="user" items="${allUsers.rows}">
					<tr>

						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td>${user.address}</td>
						<td class="btn-container">

							<form method="post">
								<input type="hidden" name="deleteEmail" value="${user.email}" />
								<button type="submit" class="delete-button">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>

	<section id="order-list">
		<div class="list-container">
			<div class="list-heading">
				<h1>Order List</h1>
			</div>
			<table class="list-table">
				<tr>
					<th>Order Id</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>User</th>

				</tr>


				<c:forEach var="order" items="${allOrders.rows}">
					<tr>

						<td>${order.order_id}</td>
						<td>${order.name}</td>
						<td>${order.price}</td>
						<td>${order.user}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
	<%
	DbConnection con = new DbConnection();

	if (request.getParameter("deleteId") != null) {
		int id = Integer.parseInt(request.getParameter("deleteId"));
		con.deleteProduct(MyConstants.PRODUCT_DELETE, id);
	}

	if (request.getParameter("deleteEmail") != null) {
		String email = request.getParameter("deleteEmail");
		con.deleteUser(MyConstants.USER_DELETE, email);
	}
	%>
	
	<footer id="footer">
    	<p>&copy; <span id="year"></span>, Puzzles, All rights reserved.</p>
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/main.js">
    </script>
</body>
</html>