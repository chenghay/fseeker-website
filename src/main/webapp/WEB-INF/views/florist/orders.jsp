<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<h3>Orders: ${orders.totalElements}</h3>
				<c:if test="${orders.totalElements gt 0 }">
					<table class="table-striped table table-condensed">
						<thead>
							<tr>
								<th>Order Id</th>
								<th>Received Date</th>
								<th>Total</th>
								<th>Customer</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:import url="snippets/orders.jsp" />
						</tbody>
					</table>
					<div class="pagination pagination-centered">
						<ul>
							<li <c:if test="${orders.firstPage}">class="disabled"</c:if>><a
								href="${pageContext.request.contextPath}/florist/orders?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${orders.number}">Prev</a></li>
							<li>
								<form method="get" class="form-inline inline">
									Page <input type="text" value="${orders.number+1}"
										name="page.page" class="input-mini" /> of ${orders.totalPages}
									Sort by: <select name="page.sort" class="input-small">
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
										<option value="20"
											<c:if test="${20 == pageSize}">selected</c:if>>20</option>
										<option value="50"
											<c:if test="${50 == pageSize}">selected</c:if>>50</option>
										<option value="100"
											<c:if test="${100 == pageSize}">selected</c:if>>100</option>
									</select>
									<button type="submit" class="btn">Go</button>
								</form>
							</li>
							<li <c:if test="${orders.lastPage}">class="disabled"</c:if>><a
								href="${pageContext.request.contextPath}/florist/orders?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${orders.number+2}">Next</a></li>
						</ul>
					</div>
				</c:if>
			</div>
		</div>

	</div>
</body>
</html>