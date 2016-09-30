<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
body {
	carouselbackground-color: grey;
}
</style>

</head>
<body>


	<h3>Welcome To Smart Mobile Shop</h3>

	<c:choose>
		<c:when test="${isAdmin==1 }">
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#">MobileShoppy</a>
					</div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>

						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="product">Product <span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="product">Add</li>
							
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown">Category <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="category">Add</li>
								
							</ul></li>

						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="supplier">Supplier <span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="supplier">Add</a></li>
								
							</ul></li>

						<ul class="nav navbar-nav navbar-right">
							<c:choose>
								<c:when test="${empty loggedInUser}">


								</c:when>
								<c:when test="${not empty loggedInUser}">
									<td>Welcome ${loggedInUser}</td>
									<li><a href="perform_logout"><span
											class="glyphicon glyphicon-log-out"></span> Logout</a></li>
								</c:when>
							</c:choose>
						</ul>
				</div>
			</nav>
		</c:when>
		<c:otherwise>
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#">MobileShoppy</a>
					</div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>

						<ul class="nav navbar-nav">
							<li class="active"><a href="#">AboutUs</a></li>

							<ul class="nav navbar-nav">
								<li class="active"><a href="#">ContactUs</a></li>
								<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="product">Product <span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								
								<li><a href="productdisplay">view all</a></li>
								</ul></li></ul>
								
								<ul class="nav navbar-nav navbar-right">
									<c:choose>
										<c:when test="${empty loggedInUser}">

											<li><a href="#"><span
													class="glyphicon glyphicon-search"></span> search</a></li>
											<li><a href="signup"><span
													class="glyphicon glyphicon-user"></span> Sign Up</a></li>
											<li><a href="signin"><span
													class="glyphicon glyphicon-log-in"></span> Login</a></li>

										</c:when>
										<c:when test="${not empty loggedInUser}">
											<td>Welcome ${loggedInUser}</td>
											<li><a href="perform_logout"><span
													class="glyphicon glyphicon-log-out"></span> Logout</a></li>
										</c:when>
									</c:choose>
								</ul>
				</div>
			</nav>
		</c:otherwise>
	</c:choose>