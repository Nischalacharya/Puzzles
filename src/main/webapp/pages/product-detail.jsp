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
<%
String prodId = request.getParameter("productId");
int productId = Integer.parseInt(prodId);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Puzzles | Product</title>
</head>

<body>
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3307/puzzles" user="root" password="" />
	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="productDetails" dataSource="${dbConnection}">
		SELECT * FROM products WHERE product_id = <%=productId%>
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

	<c:forEach var="productDetail" items="${productDetails.rows}">

		<section id="product-detail">
			<div class="container">
				<div class="product-image">
					<img class="product-img"
						src="http://localhost:8080/images/${productDetail.image}"
						alt="Productt Image">
				</div>
				<div class="product-content">
					<h1 class="product-title">${productDetail.name} - ${productDetail.brand}</h1>
					<p class="product-price">Rs. ${productDetail.price}</p>
					<p class="product-rating">Rating: ${productDetail.rating}</p>
					
					<div class="product-description"><i class="fa-solid fa-circle-info"></i>
					<span>Description</span>
					<p >${productDetail.description}</p>
					</div>
					<form action="${pageContext.request.contextPath}/AddToCart" method="post">
						<c:set var="userEmail" value="${user }" />
						<input type="hidden" name="userEmail" value="${userEmail}" />
						<input type="hidden" name="productId" value="${productDetail.product_id}" />

						<button class="btn-order">Add to Cart</button>
					</form>
					<%
					String successMessage = (String) request.getAttribute("successMessage");
					String errorMessage = (String) request.getAttribute("errorMessage");
					if (successMessage != null) {
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
			</div>
		</section>
	</c:forEach>
	
	<footer id="footer">
    	<p>&copy; <span id="year"></span>, Puzzles, All rights reserved.</p>
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/main.js">
    </script>
</body>

</html>