<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<title>Puzzles | Add Product</title>
</head>
<body>
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

	<section id="add-product">
		<form action="${pageContext.request.contextPath}/ProductAdd"
			method="post" enctype="multipart/form-data">
			<div class="input-group">
				<label for="name">Name</label> <input type="text" name="name"
					id="name" />
			</div>
			<div class="input-group">
				<label for="description">Description</label> <input type="text"
					name="description" id="description" />
			</div>
			<div class="input-group">
				<label for="category">Category</label> <input type="text"
					name="category" id="category" />
			</div>
			<div class="input-group">
				<label for="price">Price</label> <input type="number" name="price"
					id="price" />
			</div>
			<div class="input-group">
				<label for="stock">Stock</label> <input type="number" name="stock"
					id="stock" />
			</div>
			<div class="input-group">
				<label for="rating">Rating</label> <input type="number"
					name="rating" id="rating" />
			</div>
			<div class="input-group">
				<label for="brand">Brand</label> <input type="text" name="brand"
					id="brand" />
			</div>
			<div class="input-group">
				<label for="image">Image</label> <input type="file" name="image"
					id="image" />
			</div>

			<button type="submit" class="btn-add">Add Product</button>
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