<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="adminhome.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supplier</title>
</head>
<body>
<div align="center">
	<h2>Adding Supplier</h2>
	<c:url var="addAction" value="/sadd">
	</c:url>
	<form:form method="POST" action="${addAction}" commandName="supplier">
		<table>
			<tr>
				<td>Supplier Id:</td>
				<c:choose>
					<c:when test="${!empty supplier.id}">
						<td><form:input path="id" disabled="true" readonly="true" /></td>
					</c:when>
					<c:otherwise>
						<td><form:input path="id" pattern="{4,7}" required="true"
								title="id should contain 4 to 7 characters" /></td>
					</c:otherwise>
				</c:choose>
			</tr>

			<tr>
				<td>Supplier Name:</td>
				<td><form:input path="name" required="true" /></td>
			</tr>

			<tr>
				<td>Supplier Address:</td>
				<td><form:input path="address" required="true" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<h2>Supplier List</h2>
	<c:if test="${!empty supplierList}">
		<table class="table table-striped">
			<tr>
				<th>Supplier ID</th>
				<th>Supplier Name</th>
				<th>Supplier Address</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${supplierList}" var="supplier">
				<tr>
					<td>${supplier.id}</td>
					<td>${supplier.name}</td>
					<td>${supplier.address}</td>
					<td><a
						href="<c:url value='sup${supplier.id}'/>">Edit</a></td>
					<td><a
						href="<c:url value='Deletesupplier${supplier.id}'/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
</body>
</html>