<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="review" items="${reviews.content }">
	<div class="well">
		<p>
			<strong>Date: </strong>
			<fmt:formatDate type="date" value="${review.date}" />
			<strong>Rank: </strong>${review.rank } <strong>Product: </strong> <a
				href="${pageContext.request.contextPath}/florist/products/${review.product.id}">${review.product.name}</a>
		</p>
		<div>${review.comment }</div>
	</div>
</c:forEach>