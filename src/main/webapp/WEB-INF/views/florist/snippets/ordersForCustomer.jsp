<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="order" items="${orders.content}">
	<tr>
		<td><a
			href="${pageContext.request.contextPath}/florist/orders/${order.id}">${order.groupId}</a></td>
		<td><fmt:formatDate type="date" value="${order.date}" /></td>
		<td>$${order.total}</td>
		<td>${order.status }</td>
	</tr>
</c:forEach>
