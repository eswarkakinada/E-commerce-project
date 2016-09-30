<%@include file="header.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding</title>
</head>
<body>
	<center>
		<h2>Updating Category</h2>
		<form:form method="POST" action="editc" commandName="category">
			<table>
				<tr>
					<td>Category Id:</td>
					<td><form:input type="text" path="id" value="${cat.id}"
							readonly="readonly" /></td>
				</tr>
				<tr>
					<td>Category Name:</td>
					<td><form:input type="text" path="name" value="${cat.name}" /></td>
				</tr>
				<tr>
					<td>Category Description:</td>
					<td><form:input type="text" path="description"
							value="${cat.description}" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Submit" /></td>
					<td><input type="reset" value="Reset" /></td>
				</tr>
			</table>
		</form:form>
</html>
