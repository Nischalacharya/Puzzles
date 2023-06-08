<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<title>Puzzles | Update Product</title>
</head>
<body>
	<nav id="navbar">
		<h1 class="logo">Puzzles</h1>
		<ul>
			<li><a href="../index.jsp">Home</a></li>
			<li><a href="./product.jsp">Product</a></li>
			<li><a href="">About</a></li>
			<li><a href="">Contact</a></li>
		</ul>

		<div class="logo-container">
			<!-- <span>Login</span> -->
			<a href="${pageContext.request.contextPath}/pages/admin.jsp"><i
				class="fa-solid fa-user"></i></a> <i class="fa-solid fa-cart-shopping"></i>
			<i class="fa-solid fa-right-from-bracket"></i>
		</div>
	</nav>



	<section id="add-product">
		<form action="${pageContext.request.contextPath}/ProductUpdate"
			method="post" enctype="multipart/form-data">
			<c:if test="${product != null}">
				<input type="text" name="id"
					value="<c:out value='${product.product_id}' />" />
			</c:if>

			<h1>
				<%
				String id = request.getParameter("products");
				%>
			</h1>



			<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost:3307/puzzles" user="root" password="" />
			<!-- Executing Query Using SQL Tag Library -->
			<sql:query var="allProducts" dataSource="${dbConnection}">
				SELECT * FROM products WHERE product_id = '<%=id%>'
			</sql:query>



			<c:forEach var="product" items="${allProducts.rows}">
				<c:if test="${product != null}">
					<input type="hidden" name="id" value="${product.product_id}" />
				</c:if>

				<div class="input-group">
					<label for="name">Name</label> <input type="text" name="name"
						value="<c:out value='${product.name}' />" id="name" />
				</div>
				<div class="input-group">
					<label for="description">Description</label> <input type="text"
						value="<c:out value='${product.description}' />"
						name="description" id="description" />
				</div>
				<div class="input-group">
					<label for="category">Category</label> <input type="text"
						value="<c:out value='${product.category}' />" name="category"
						id="category" />
				</div>
				<div class="input-group">
					<label for="price">Price</label> <input type="number" name="price"
						value="<c:out value='${product.price}' />" id="price" />
				</div>
				<div class="input-group">
					<label for="stock">Stock</label> <input type="number" name="stock"
						value="<c:out value='${product.stock}' />" id="stock" />
				</div>
				<div class="input-group">
					<label for="rating">Rating</label> <input type="number"
						name="rating" value="<c:out value='${product.rating}' />"
						id="rating" />
				</div>
				<div class="input-group">
					<label for="brand">Brand</label> <input type="text" name="brand"
						id="brand" value="<c:out value='${product.brand}' />" />
				</div>
				<div class="input-group">
					<label for="image">Image</label> <input type="file" name="image"
						id="image" /> <span>${product.image}</span>
				</div>
			</c:forEach>

			<button type="submit" class="btn-add">Update Product</button>
		</form>
	</section>

	<footer id="footer">
		<p>
			&copy; <span id="year"></span>, Puzzles, All rights reserved.
		</p>
	</footer>

	<script src="${pageContext.request.contextPath}/js/main.js">
		
	</script>
</body>
</html>