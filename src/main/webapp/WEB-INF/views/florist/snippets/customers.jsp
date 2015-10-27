<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="customer" items="${customers.content}">
	<tr>
		<td><a
			href="${pageContext.request.contextPath}/florist/customers/${customer.username}">${customer.firstname} ${customer.lastname }</a></td>
		<td>${customer.email}</td>
		<td>${orders[customer.username]}</td>
		<td>${reviews[customer.username]}</td>
	</tr>
</c:forEach>