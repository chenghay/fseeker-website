<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

				<h3>Orders</h3>

				<table class="table-striped table table-condensed">
					<thead>
						<tr>
							<th>Date</th>
							<th>Confirmation ID</th>
							<th>Store</th>
							<th>Total Price</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr>
								<td><fmt:formatDate type="date" value="${order.date}" /></td>
								<td><a
									href="${pageContext.request.contextPath}/user/orders/${order.id}">${order.groupId}</a></td>
								<td>${order.store.storename}</td>
								<td>$${order.total}</td>
								<td>${order.status }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>