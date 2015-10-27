<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
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
				<h3>Orders placed to store: ${store.storename}</h3>

				<c:forEach var="orders" items="${order.content}">
					<div class="well">
						<table class="table-striped table table-condensed">
							<thead>
								<tr>
									<th>Customer</th>
									<th>Date</th>
									<th>Confirmation ID</th>

									<th>Pickup</th>
									<th>Total</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>

								<tr>
									<td>${orders.customer.firstname}
										${orders.customer.lastname}</td>
									<td><fmt:formatDate type="date" value="${orders.date}" />}</td>
									<td>${orders.groupId}</td>
									<td><c:choose>
											<c:when test="${orders.pickup }">
												<i class="icon-ok"></i>
											</c:when>
											<c:otherwise>
												<i class="icon-remove"></i>
											</c:otherwise>
										</c:choose></td>

									<td>$${orders.total}</td>
									<td>${orders.status}</td>
								</tr>

							</tbody>
						</table>
						<div class="alert alert-success">
							<table class="table-striped table table-condensed">
								<thead>
									<tr>
										<th></th>
										<th>Product name</th>
										<th>Quantity</th>
										<th>Price</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="orderEntries" items="${orders.orderEntries}">
										<tr>
											<td><t:image product="${orderEntries.product }"
													clazz="table" /></td>
											<td>${orderEntries.product.name}</td>
											<td>${orderEntries.quantity}</td>
											<td>$${orderEntries.price }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>

		<div class="pagination pagination-right">
			<ul>
				<li <c:if test="${order.firstPage}">class="disabled"</c:if>><a
					href="${pageContext.request.contextPath}/admin/stores/${store.id}/orders?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${order.number}">Prev</a></li>
				<li>
					<form method="get" class="form-inline inline">
						Page <input type="text" value="${order.number+1}" name="page.page"
							class="input-mini" /> of ${order.totalPages} Sort by: <select
							name="page.sort" class="input-small">
							<option value="date"
								<c:if test="${'date' == pageSort}">selected</c:if>>Date</option>
							<option value="total"
								<c:if test="${'total' == pageSort}">selected</c:if>>Total</option>
						</select> Dir: <select name="page.sort.dir" class="input-mini">
							<option value="DESC"
								<c:if test="${'DESC' == pageSortDir}">selected</c:if>>DES</option>
							<option value="ASC"
								<c:if test="${'ASC' == pageSortDir}">selected</c:if>>ASC</option>
						</select> Items per page: <select name="page.size" class="input-mini">
							<option value="10" <c:if test="${10 == pageSize}">selected</c:if>>10</option>
							<option value="20" <c:if test="${20 == pageSize}">selected</c:if>>20</option>
							<option value="50" <c:if test="${50 == pageSize}">selected</c:if>>50</option>
						</select>
						<button type="submit" class="btn">Go</button>
					</form>
				</li>
				<li <c:if test="${order.lastPage}">class="disabled"</c:if>><a
					href="${pageContext.request.contextPath}/admin/stores/${store.id}/orders?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${order.number+2}">Next</a></li>
			</ul>
		</div>
	</div>
</body>
</html>