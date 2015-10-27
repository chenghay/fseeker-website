<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<h3>${store.name }</h3>
	<ul class="nav nav-list">
		<li class="nav-header">Store Details</li>
		<li><strong>Name:</strong></li>
		<li>${product.store.storename }</li>
		<li><strong>Phone Number:</strong></li>
		<li>${product.store.phone}</li>
		<li><strong>Email:</strong></li>
		<li>${product.store.email}</li>
		<li><strong>Location:</strong></li>
		<li>${product.store.location.address1}
			${product.store.location.address2}</li>
		<li>${product.store.location.city},
			${product.store.location.state}, ${product.store.location.zip}</li>

		<li class="nav-header">Product Details</li>
		<li><strong>Description:</strong></li>
		<li>${product.description }</li>
		<li><strong>Price:</strong> $${product.price }</li>
		<li><strong>In Stock:</strong> <c:choose>
					<c:when test="${product.available }">
						<i class="icon-ok"></i>
					</c:when>
					<c:otherwise>
						<i class="icon-remove"></i>
					</c:otherwise>
				</c:choose></li>
		<li><strong>Occasions:</strong> <c:forEach var="occasion"
				items="${product.occasions }">
				<p>${occasion.name}</p>
			</c:forEach></li>
		<li><strong>Average Review Rating:</strong>
			<p>
				<c:forEach var="i" begin="1" end="${product.getAverageRank()}"
					step="1">
					<i class="icon-star"></i>
				</c:forEach>

				<c:forEach var="i" begin="${product.getAverageRank()}" end="4"
					step="1">
					<i class="icon-star-empty"></i>
				</c:forEach>
			</p></li>
	</ul>
</div>