<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<c:forEach var="product" items="${products.content}">
	<tr>
		<td><t:image product="${product }" clazz="table"/></td>
		<td><a
			href="${pageContext.request.contextPath}/florist/products/${product.id}">${product.name}</a></td>
		<td><fmt:formatDate type="date" value="${product.added}" /></td>
		<td>${product.price}</td>
		<td><c:choose>
				<c:when test="${product.available}">
					<i class="icon-ok"></i>
				</c:when>
				<c:otherwise>
					<i class="icon-remove"></i>
				</c:otherwise>
			</c:choose></td>
		<td>${fn:length(product.reviews)}</td>
		<c:if test="${store.subscribed }">
			<td><c:choose>
					<c:when test="${product.tracking}">
						<i class="icon-ok"></i>
					</c:when>
					<c:otherwise>
						<i class="icon-remove"></i>
					</c:otherwise>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${product.pickup }">
						<i class="icon-ok"></i>
					</c:when>
					<c:otherwise>
						<i class="icon-remove"></i>
					</c:otherwise>
				</c:choose></td>
		</c:if>
	</tr>
</c:forEach>