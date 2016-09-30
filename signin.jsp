<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>signinpage</title>
<style>
body {
	background-image: url("resources/images/a.gif");
	background-repeat: no-repeat;
	background-position: right top;
	margin-right: 200px;
}
</style>
</head>
<body>


	<form method="post" action="perform_login">
		<br><br><br> <br><br><br><br>		
		<table>
			<h2>Signin</h2>

			<tr>
				<td>UserName</td>
				<td><input type="text" name="username" required></td>
			</tr>
			<tr>
				<td>Enter password</td>
				<td><input type="password" name="password"></td>
				<br>
			</tr>
		</table>


		<div style="float: center;">
			<table>
				<td><input type="submit" value="login"></td>
			</table>
		</div>
	</form>

</body>
</html>