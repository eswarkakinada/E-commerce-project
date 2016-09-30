<%@include file="header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add product</title>
</head>
<body>
	<form:form method="POST" action="aprod" commandName="product"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Product Id</td>
				<td><form:input type="text" path="id" /></td>
			</tr>
			<tr>
				<td>Prod Name</td>
				<td><form:input type="text" path="name" /></td>
			</tr>
			<tr>
				<td> Description</td>
				<td><form:input type="text" path="description" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><form:input type="number" path="price" /></td>
			</tr>
			<tr>
				<td> Quantity</td>
				<td><form:input type="number" path="quantity" /></td>
			</tr>
			
			<tr>
				<td>Category</td>
				<td><form:select path="category.id" items="${categoryList}"
						itemValue="id" itemLabel="id"></form:select></td>
			</tr>
			<tr>
				<td>Supplier</td>
				<td><form:select path="supplier.id" items="${supplierList}"
						itemValue="id" itemLabel="id"></form:select></td>
			</tr>
			<tr>
				<td>Prod Image</td>
				<td><form:input type="file" path="image" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <input
					type="submit" value="Cancel" /></td>
			</tr>
		</table>
	</form:form>
<h2>Product List</h2>
<c:if test="${!empty productList}">
	<table>
		<tr>
			<th>Product ID</th>
			<th>Product Name</th>
			<th>Product Description</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>category ID</th>
			<th>Supplier Id</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${productList}" var="product">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>
				<td>${product.quantity}</td>
				<td>${product.category.id}</td>
				<td>${product.supplier.id}</td>
				<td><a href="<c:url value='eproduct${product.id}'/>">Edit</a></td>
				<td><a href="<c:url value='dproduct${product.id}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>

</html>