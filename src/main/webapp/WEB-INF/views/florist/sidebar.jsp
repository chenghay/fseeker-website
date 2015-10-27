<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<h3><a href="${pageContext.request.contextPath}/florist/profile">${store.storename }</a></h3>
	<ul class="nav nav-list">
		<li class="nav-header">Store Management</li>
		<li><a href="${pageContext.request.contextPath}/florist/profile/modify">Update Store Info</a></li>
		<li><a href="${pageContext.request.contextPath}/florist/delivery">Store Delivery Area</a></li>
		<li><a href="${pageContext.request.contextPath}/florist/subscription">Subscription</a></li>
		<c:if test="${store.subscribed}">
		  <li><a href="${pageContext.request.contextPath}/florist/hours">Store Pickup Hours</a></li>
		</c:if>
		<li class="nav-header">Product Management</li>
		<li><a href="${pageContext.request.contextPath}/florist/products">All Products</a></li>
		<li><a href="${pageContext.request.contextPath}/florist/products/add">Add Product</a></li>
		<li class="nav-header">Transactions</li>
		<li><a href="${pageContext.request.contextPath}/florist/orders">Orders</a></li>
		<li><a href="${pageContext.request.contextPath}/florist/customers">Customers</a></li>
		<li><a href="${pageContext.request.contextPath}/florist/reviews">Reviews</a></li>
		

	</ul>
</div>