<%@include file ="header.jsp"%>


<div>
<div class="container">
  <div ng-app="myApp" ng-controller="dataCtrl">

    <hr></hr>
    <table class="table table-striped">
    <tr>
    <th>Id</th>
    <th>Product Name</th>
    <th>Product Price</th>
         <th>Product Image</th>
         <th>Quantity</th>
         
    </tr>
        <tr >
             <td>${product.id}</td>
            <td>${product.name}</td>
             <td>${product.description}</td>
            <td>${product.price}</td>
            
            <td><img src="resources/images/${product.id}.jpg" style=height:100px;width:100px;"/></td>
            <%-- <td>
						<form method="post"
							action="buy${loggedInUserID}&${product.id}">
							Quantity: <input type="text" name="quantity" /> <input
								type="submit" value="Submit" />
						</form>
					</td> --%>
            
        </tr>    
    </table>
</div>

<%@include file ="footer.jsp"%>