<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<div class="well">
	<table class="table-striped table table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Product Description</th>
				<th>Quantity</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orr" items="${items}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/products/${orr.product.id}">
					<t:image product="${orr.product }" clazz="table"/></a></td>
					<td><a  href="${pageContext.request.contextPath}/products/${orr.product.id}">${orr.product.name}</a></td>
					<td>$${orr.price}</td>
					<td>${orr.product.description}</td>
					<td>${orr.quantity}</td>
					<td>
						<button class="btn btn-danger" type="button"
							onClick="funcionUpdateCartde('${orr.product.id}');">Remove
							from Cart</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
<c:choose>
	<c:when test="${not empty items}">
		<a class="btn btn-primary"
			href="${pageContext.request.contextPath}/user/cart/PlaceOrder">Place
			Order</a>
	</c:when>
	<c:otherwise>
		<p>Cart empty</p>
		<br>
	</c:otherwise>
</c:choose>