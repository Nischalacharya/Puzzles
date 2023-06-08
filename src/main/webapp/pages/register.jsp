<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<title>Puzzles | Register</title>
</head>

<body>
	<div id="register">
		<form action="${pageContext.request.contextPath}/RegisterServlet" class="form" method="post" enctype="multipart/form-data">
			<h1>Sign In</h1>
			<div class="input-group">
				<div>
					<label for="name">Name</label>
					<input id="name" type="text" name = "userName"/>
				</div>
				<div>
					<label for="phone">Contact number</label>
					<input id="phone" type="text" name = "phone" />
				</div>
				<div>
					<label for="address">Address</label>
					<input id="address" type="text" name = "address"/>
				</div>
				<div>
					<label for="email">Email</label>
					<input id="email" type="email" name = "email" />
				</div>
				<div>
					<label for="password">Password</label>
					<input id="password" type="password" name = "password"/>
				</div>
				<div>
					<label for="confirm-password">Confirm Password</label>
					<input id="confirm-password" type="password" name = "confirmPassword"/>
				</div>
				<div>
					<label for="profile-image">Profile Image</label>
					<input id="image" type="file" name = "image" />
				</div>
			</div>
			<button class="btn" type="submit">Sign In</button>
			<p>Already have an account? <span><a href="./login.jsp">Login</a></span></p>
		</form>
	</div>
</body>

</html>