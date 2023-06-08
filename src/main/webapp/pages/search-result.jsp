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
String searchInput = request.getParameter("searchInput");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="../css/style.css" />
<title>Search Result</title>
</head>
<body>
	<%
	if (searchInput == "") {
		request.setAttribute("errorMessage", "Invalid input!!");
		request.getRequestDispatcher("product.jsp").forward(request, response);
	} else {
	%>
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3307/puzzles" user="root" password="" />
	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="searchProducts" dataSource="${dbConnection}">
		SELECT * FROM products WHERE name LIKE '%<%=searchInput%>%'
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

	<section id="products">
		<c:if test="${empty searchProducts.rows}">
			<h2>
				No results found for "<%=searchInput%>"
			</h2>
		</c:if>
		<c:if test="${not empty searchProducts.rows}">
			<h2>
				Search result for "<%=searchInput%>"
			</h2>
			<div class="product-container">
				<c:forEach var="products" items="${searchProducts.rows}">
					<a href="./product-detail.html" class="product"> <img
						class="product-img"
						src="http://localhost:8080/images/${products.image}"
						alt="Productt Image">

						<div>
							<div>
								<h4>${products.name }</h4>
								<p>Price: ${products.price }</p>
							</div>
							<form action="product-detail.jsp">
								<input type="hidden" name="productId"
									value="${products.product_id}">
								<button class="btn-view" type="submit">&rarr;</button>
							</form>
						</div>
					</a>
				</c:forEach>

			</div>
		</c:if>
	</section>
	<%
	}
	%>

	<footer id="footer">
    	<p>&copy; <span id="year"></span>, Puzzles, All rights reserved.</p>
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/main.js">
    </script>
</body>
</html>