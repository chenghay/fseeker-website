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

				<h3>Products: ${products.totalElements}</h3>
				<c:if test="${store.subscribed }">
					<h4>Membership Options:</h4>
					<span>Tracking for all:</span>
					<form method="post" class="form-inline inline">
						<input type="hidden" name="tracking" value="true" />
						<button type="submit" class="btn">Enable</button>
					</form>
					<form method="post" class="form-inline inline">
						<input type="hidden" name="tracking" value="false" />
						<button type="submit" class="btn">Disable</button>
					</form>
					<span>Pickup for all:</span>
					<form method="post" class="form-inline inline">
						<input type="hidden" name="pickup" value="true" />
						<button type="submit" class="btn">Enable</button>
					</form>
					<form method="post" class="form-inline inline">
						<input type="hidden" name="pickup" value="false" />
						<button type="submit" class="btn">Disable</button>
					</form>
					<p></p>
				</c:if>
				<table class="table-striped table table-condensed">
					<thead>
						<tr>
							<th></th>
							<th>Name</th>
							<th>Date Added</th>
							<th>Price</th>
							<th>Availability</th>
							<th>Reviews</th>
							<c:if test="${store.subscribed }">
								<th>Tracking Enabled</th>
								<th>Local Pickup</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:import url="snippets/products.jsp" />
					</tbody>
				</table>
				<div class="pagination pagination-centered">
					<ul>
						<li <c:if test="${products.firstPage}">class="disabled"</c:if>><a
							href="${pageContext.request.contextPath}/florist/products?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${products.number}">Prev</a></li>
						<li>
							<form method="get" class="form-inline inline">
								Page <input type="text" value="${products.number+1}"
									name="page.page" class="input-mini" /> of
								${products.totalPages} Sort by: <select name="page.sort"
									class="input-small">
									<option value="name"
										<c:if test="${'name' == pageSort}">selected</c:if>>Name</option>
									<option value="added"
										<c:if test="${'added' == pageSort}">selected</c:if>>Date</option>
									<option value="price"
										<c:if test="${'price' == pageSort}">selected</c:if>>Price</option>
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
						<li <c:if test="${products.lastPage}">class="disabled"</c:if>><a
							href="${pageContext.request.contextPath}/florist/products?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${products.number+2}">Next</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>