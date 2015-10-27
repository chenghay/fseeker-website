<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">

	function funcionUpdateCartPub(){
	
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/pubCart/add/${productToReview.id}",
			success: function(response){
				$("#subViewDiv").html(response);
			}
		});
	}
	
		function funcionUpdateCartdePub(id){
		
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/pubCart/delete/"+id,
			success: function(response){
				$("#subViewDiv").html(response);
			}
		});
	}
		
		function funcionUpdateCart(){
			
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/cart/add/${productToReview.id}",
				success: function(response){
					$("#subViewDiv").html(response);
				}
			});
		}
		
			function funcionUpdateCartde(id){
			
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/cart/delete/"+id,
				success: function(response){
					$("#subViewDiv").html(response);
				}
			});
		}
	
</script>
<c:import url="commonHeader.jsp" />
<title>FlowerSeeker</title>
</head>
<body>
	<c:import url="topNavBar.jsp" />

	 	<div class="container">
		<div class="row">
			<div class="span3">
				 
			</div>
			<div class="span9">
			<center>
			<h3>${productToReview.name}</h3>
			</center>
				<table class="table-striped table table-condensed">
					<thead>
						<tr>
							<th> </th>
							<th><b>Product characteristics:</b> </th>
							<th></th>							
						</tr>
					</thead>
					<tbody>						 
							<tr>
								 
								<td><img src="${pageContext.request.contextPath}/static/uploaded/product/${productToReview.id}/${productToReview.image}" width="300" height="300" /></td>
								<td>
								
								<table class="table-striped table table-condensed">
								
								<tr><td><p><b>Product Name:</b></p> <p>${productToReview.name}</p></td></tr>
								
								<tr><td><p><b>Price:</b></p> <p>${productToReview.price}</p></tp></tr>
								
								<tr><td><p><b>Description:</b></p> <p>${productToReview.description}</p></td></tr>
								
								<tr><td><p><b>Occasions:</b></p></td></tr>
								<c:forEach var="ocation" items="${productToReview.occasions}">
									<tr><td><p>${ocation.name}</p></td></tr>
								</c:forEach>
								</table>
							
							
								 <c:choose>
									<c:when test="${productToReview.pickup}">
										<tr><td><p><b>You can pickup this order:</b></p></td></tr>
									</c:when> 
   									<c:otherwise> 
   										<tr><td><p><b>Does not provide pick up order:</b></p></td></tr> 
   									</c:otherwise>  
								</c:choose>
								 
								 <c:choose>
									<c:when test="${productToReview.tracking}">
										<tr><td><p><b>Allows tracking order:</b></p></td></tr>
									</c:when> 
   									<c:otherwise> 
   										<tr><td><p><b>Does not provide tracking order:</b></p></td></tr> 
   									</c:otherwise>  
								</c:choose>
								 
							
							 
							
							
								</td>	 
							</tr>	
							<tr>	
							<td></td>	
							<td></td>	
							<td>
							<form:form method="post" enctype="multipart/form-data" commandName="addOrder">
								  	<br><br>
								  	<sec:authorize access="isAnonymous()">
											<button class="btn btn-success" type="button" onclick="funcionUpdateCartPub();">Add to Cart</button>
									</sec:authorize>
					
									<sec:authorize access="hasRole('customer')">
											<button class="btn btn-success" type="button" onclick="funcionUpdateCart();">Add to Cart</button>
									</sec:authorize>												
									
								</form:form>							
							</td>				
								
							</tr>
					</tbody>
					

				</table>
				
				<sec:authorize access="isAnonymous()">
						<div id="subViewDiv"><c:import url="publicCart.jsp" /></div>
				</sec:authorize>

				<sec:authorize access="hasRole('customer')">
						<div id="subViewDiv"><c:import url="cart.jsp" /></div>
				</sec:authorize>
								
			</div>
		</div>
	</div>
</body>
</html>