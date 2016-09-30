<%@include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <link rel="stylesheet" href="<c:url value= "/resources/admin.css"/>">
 <html>
 <head>
 <title>Category Page</title>
 </head>
 
 <body>
<div align="center">
 ${msg}
 <h1>Add a Category</h1>
 <c:url var="addAction" value="/categoryadd"> </c:url>
 <form:form action="${addAction}" commandName="category">
 <table>
 <tr>
 <td>Category Id:</td>
 <c:choose>
 <c:when test="${!empty category.id}">
 <td><form:input path=""  value=""/></td>
 </c:when>
 <c:otherwise>
 <td><form:input path="id" pattern="{4,7}" required="true" title="id should contain 4 to 7 characters"/></td>
 </c:otherwise>
 </c:choose>
 </tr>
 
 <tr>
 <td>Category Name:</td>
 <td><form:input path="name" required="true" value=""/></td>
 </tr>
 
<tr>
<td>Category Description:</td>
 <td><form:input path="description" required="true" value=""/></td>
 </tr>
 <tr>
 <%-- <td colspan="2"><c:if test="${!empty category.name}">
 <input type="submit" value="Update Category"/>
</c:if></td> --%>
<td>
 <input type="submit" value="Add Category"/></td>
</tr>
</table>
</form:form>
<br>
<h3>Category List</h3>
<c:if test="${!empty categoryList}">
<table class= "table table-striped">
<tr>
<th width ="80">Category ID</th>
<th width ="120">Category Name</th>
<th width ="120">Category Description</th>
<th width ="60">Update</th>
<th width ="60">Delete</th>
</tr>

<c:forEach items="${categoryList}" var="category">
<tr>
<td>${category.id}</td>
<td>${category.name}</td>
<td>${category.description}</td>
<td><a href="<c:url value='update${category.id}'/>">Update</a></td>
<td><a href="<c:url value='Deletecategory${category.id}'/>">Delete</a></td>
</tr>
</c:forEach>
</table>
</c:if>
</div>
</body>
 </html>