<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../commonHeader.jsp" />
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
				<h3>Order Detail</h3>

				<table class="table-striped table table-condensed">
					<thead>
						<tr>
							<th></th>
							<th>Product</th>
							<th>Quantity</th>
							<th>Price</th>
							<c:if test="${store.subscribed }">
								<th>Local Pickup</th>
								<th>Status</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entry" items="${order.orderEntries}">
							<tr>
								<td><t:image product="${entry.product }" clazz="table"/></td>
								<td><a
									href="${pageContext.request.contextPath}/florist/products/${entry.product.id}">${entry.product.name}</a></td>
								<td>${entry.quantity}</td>
								<td>$${entry.price}</td>
								<c:if test="${store.subscribed }">

									<td><c:choose>
											<c:when test="${product.pickup }">
												<i class="icon-ok"></i>
											</c:when>
											<c:otherwise>
												<i class="icon-remove"></i>
											</c:otherwise>
										</c:choose></td>
									<td>${entry.status}</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hr />
				<h4>Details</h4>
				<p>
					<strong>Id: </strong>${order.groupId}
				</p>
				<p>
					<strong>Date: </strong>
					<fmt:formatDate type="date" value="${order.date}" />
				</p>
				<p>
					<strong>Total: </strong>$${order.total } USD
				</p>
				<p>
					<strong>Customer: </strong><a
						href="${pageContext.request.contextPath}/florist/customers/${order.customer.username}">${order.customer.firstname}
						${order.customer.lastname}</a>
				</p>
				<p>
					<strong>Status: </strong><span id="status">${order.status }</span>
					<button id="changeStatus" class="btn">Change</button>
				</p>
				<p>
					<strong>Destination: </strong>
				</p>
				<address>
					${order.destination.address1}<br> ${order.destination.city},
					${order.destination.state} ${order.destination.zip}<br>
				</address>
				<p>
					<strong>Special Instructions:</strong>
				</p>
				<div>${order.instruction }</div>
			</div>
		</div>

	</div>
</body>
</html>