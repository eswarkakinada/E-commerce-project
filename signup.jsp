
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>

<title>signup page</title>
<style>
body {
    background-image: url("resources/images/b2.jpg");
     background-repeat: no-repeat; 
}
</style>

</head>
<body>
<br><br><br><br><br><br>
    <font color="white">

<h2>Signup</h2>
	<form:form method="POST" action="register" commandName="userDetails">
		<table>
			<tr>
				<td>Enter Name</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input type="password" path="password" /></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><form:input type="password" path="cpassword" /></td>
			</tr>
			<tr>
				<td>Email </td>
				<td><form:input path="emailid" /></td>
			</tr>
			<tr>
			<tr>
				<td>Contact</td>
				<td><form:input path="contact" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><form:input path="address" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" />
				
				 <input
					type="submit" value="Cancel" /></td>
			</tr>
		</table>
	</form:form>

</body>

</html>