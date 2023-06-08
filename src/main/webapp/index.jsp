<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Including core tag library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Including formatting tag library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- Including SQL tag library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>Document</title>
</head>

<body>
	<!-- Database connection using SQL Tag Library -->
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3307/puzzles" user="root" password=""/>
	<!-- Executing Query Using SQL Tag Library -->
	<sql:query var="allProducts" dataSource="${dbConnection}">
		SELECT * FROM products ORDER BY product_id DESC LIMIT 4;
	</sql:query>
	<sql:query var="allUnisexProducts" dataSource="${dbConnection}">
		SELECT * FROM products WHERE category="Unisex" ORDER BY product_id DESC LIMIT 4;
	</sql:query>
	<sql:query var="allMaleProducts" dataSource="${dbConnection}">
		SELECT * FROM products WHERE category="Male" ORDER BY product_id DESC LIMIT 4;
	</sql:query>
	<sql:query var="allFemaleProducts" dataSource="${dbConnection}">
		SELECT * FROM products WHERE category="Female" ORDER BY product_id DESC LIMIT 4;
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

    <!-- Showcase -->
    <section id="showcase">
        <h1>STYLIST PICKS BEAT THE HEAT</h1>
        <a class="btn" href="${pageContext.request.contextPath}/pages/product.jsp">Shop Now</button>
    </section>

	
	
    <!-- New Arrivals -->
    <section id="new-arrivals">
        <h2>Discover NEW Arrivals</h2>
        <div class="product-container">
	        <c:forEach var="product" items="${allProducts.rows}">
		        <a href="./pages/product-detail.jsp" class="product">
	                <img class="product-img" src="http://localhost:8080/images/${product.image}" alt="Product image">
	                <div>
	                    <h4>${product.name}</h4>
	                    <p>Rs. ${product.price}</p>
	                </div>
	            </a>
			</c:forEach>            
        </div>
    </section>
    
    <!-- Unisex section -->
    <section id="unisex">
        <h2>Discover UNISEX Section</h2>
        <div class="product-container">
	        <c:forEach var="product" items="${allUnisexProducts.rows}">
		        <a href="./pages/product-detail.jsp" class="product">
	                <img class="product-img" src="http://localhost:8080/images/${product.image}" alt="Product image">
	                <div>
	                    <h4>${product.name}</h4>
	                    <p>Rs. ${product.price}</p>
	                </div>
	            </a>
			</c:forEach>     
        </div>
    </section>
    
    <!-- Male section -->
    <section id="male">
        <h2>Discover MALE Section</h2>
        <div class="product-container">
	        <c:forEach var="product" items="${allMaleProducts.rows}">
		        <a href="./pages/product-detail.jsp" class="product">
	                <img class="product-img" src="http://localhost:8080/images/${product.image}" alt="Product image">
	                <div>
	                    <h4>${product.name}</h4>
	                    <p>Rs. ${product.price}</p>
	                </div>
	            </a>
			</c:forEach> 
        </div>
    </section>
    
    <!-- Female section -->
    <section id="female">
        <h2>Discover FEMALE Section</h2>
        <div class="product-container">
	        <c:forEach var="product" items="${allFemaleProducts.rows}">
		        <a href="./pages/product-detail.jsp" class="product">
	                <img class="product-img" src="http://localhost:8080/images/${product.image}" alt="Product image">
	                <div>
	                    <h4>${product.name}</h4>
	                    <p>Rs. ${product.price}</p>
	                </div>
	            </a>
			</c:forEach>        
        </div>
    </section>
    
    <footer id="footer">
    	<p>&copy; <span id="year"></span>, Puzzles, All rights reserved.</p>
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/main.js">
    </script>
</body>

</html>