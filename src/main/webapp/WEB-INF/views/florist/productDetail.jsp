<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../commonHeader.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#orderPageForm').ajaxForm({
			target : '#orders'
		});
		$('#reviewPageForm').ajaxForm({
			target : '#reviews'
		});
	});
</script>
<title>FlowerSeeker</title>
</head>
<body>
	<c:import url="../topNavBar.jsp" />

	<div class="container">
		<div class="row">
			<div class="span3">
				<c:import url="sidebar.jsp" />
			</div>
			<div class="span9">
				<h3>Product Detail</h3>
				<div class="row">
					<div class="span5">
						<t:image product="${product }" clazz="reg"/>
						</div>
					<div class="span4">
						<p>
							<strong>Name: </strong>${product.name }
						</p>
						<p>
							<strong>Description:</strong>
						</p>
						<div>${product.description }</div>
						<br>
						<p>
							<strong>Current Price: </strong>$${product.price }
						</p>
						<p>
							<strong>Available: </strong>
							<c:choose>
								<c:when test="${product.available}">
									<i class="icon-ok"></i>
								</c:when>
								<c:otherwise>
									<i class="icon-remove"></i>
								</c:otherwise>
							</c:choose>
						</p>
						<p>
							<strong>Occasions: </strong>
							<c:forEach var="occasion" items="${product.occasions }">${occasion.name} </c:forEach>
						</p>
						<c:if test="${store.subscribed }">
							<p>
								<strong>Local Pickup: </strong>
								<c:choose>
									<c:when test="${product.pickup}">
										<i class="icon-ok"></i>
									</c:when>
									<c:otherwise>
										<i class="icon-remove"></i>
									</c:otherwise>
								</c:choose>
							</p>
							<p>
								<strong>Tracking Enabled: </strong>
								<c:choose>
									<c:when test="${product.tracking}">
										<i class="icon-ok"></i>
									</c:when>
									<c:otherwise>
										<i class="icon-remove"></i>
									</c:otherwise>
								</c:choose>
							</p>
						</c:if>
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath}/florist/products/${product.id}/modify">Modify</a>
					</div>
				</div>
<hr/>
				<div>
					<h4>In Orders: ${orders.totalElements}</h4>
					<c:if test="${orders.totalElements gt 0}">
						<table class="table-striped table table-condensed">
							<thead>
								<tr>
									<th>Order Id</th>
									<th>Received Date</th>
									<th>Total</th>
									<th>Customer</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody id="orders">
								<c:import url="snippets/ordersForProduct.jsp" />
							</tbody>
						</table>
						<form id="orderPageForm" method="get"
							action="${pageContext.request.contextPath}/florist/products/${product.id}/orders"
							class="form-inline inline">
							Orders Page <input type="text" value="${orders.number+1}"
								name="page.page" class="input-mini" /> of ${orders.totalPages}
							Sort by: <select name="page.sort" class="input-small">
								<option value="date">Date</option>
								<option value="total">Total</option>
							</select> Dir: <select name="page.sort.dir" class="input-mini">
								<option value="DESC">DES</option>
								<option value="ASC">ASC</option>
							</select> Items per page: <select name="page.size" class="input-mini">
								<option value="10">10</option>
								<option value="30">30</option>
								<option value="50">50</option>
							</select>
							<button type="submit" class="btn">Go</button>
						</form>
					</c:if>
				</div>
<hr/>
				<div>
					<h4>Reviews: ${reviews.totalElements}</h4>
					<c:if test="${reviews.totalElements gt 0}">
						<div id="reviews">
							<c:import url="snippets/reviewsForProduct.jsp" />
						</div>
						<form id="reviewPageForm" method="get"
							action="${pageContext.request.contextPath}/florist/products/${product.id}/reviews"
							class="form-inline inline">
							Reviews Page <input type="text" value="${reviews.number+1}"
								name="page.page" class="input-mini" /> of ${reviews.totalPages}
							Sort by: <select name="page.sort" class="input-small">
								<option value="date">Date</option>
								<option value="rank">Rank</option>
							</select> Dir: <select name="page.sort.dir" class="input-mini">
								<option value="DESC">DES</option>
								<option value="ASC">ASC</option>
							</select> Items per page: <select name="page.size" class="input-mini">
								<option value="10">10</option>
								<option value="30">30</option>
								<option value="50">50</option>
							</select>
							<button type="submit" class="btn">Go</button>
						</form>
					</c:if>
				</div>

			</div>
		</div>
	</div>
</body>
</html>