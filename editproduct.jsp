<%@include file="adminhome.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="col-sm-9">
	<form:form action="updateprod" method="POST" commandName="product"
		enctype="multipart/form-data">
		<table>
			<h2>Updating Product</h2>
			<tr>
				<td>Product Id</td>
				<td><form:input type="text" path="id"
						value="${product.id}" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><form:input type="text" path="name" value="${product.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="description">description</form:label></td>
				<td><form:input path="description"
						value="${product.description}" /></td>
			</tr>
			<tr>
				<td><form:label path="price">price</form:label></td>
				<td><form:input path="price" value="${product.price}" /></td>
			</tr>
			<tr>
				<td><form:label path="quantity">quantity</form:label></td>
				<td><form:input path="quantity" value="${product.quantity}" /></td>
			</tr>
			<tr>
				<td>Category Id:</td>
				<td><form:select path="category.id" items="${categoryList}"
						itemValue="id" itemLabel="id"></form:select></td>
			</tr>
			<tr>
				<td>Supplier Id:</td>
				<td><form:select path="supplier.id" items="${supplierList}"
						itemValue="id" itemLabel="id"></form:select></td>
			</tr>
			<tr>
				<td><form:label path="image">ProductImage</form:label></td>
				<td><form:input type="file" path="image"
						value="${product.image}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <input
					type="submit" value="Cancel" /></td>
			</tr>
		</table>
	</form:form>
</div>