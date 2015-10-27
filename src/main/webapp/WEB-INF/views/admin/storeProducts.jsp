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
				<h3>Products added by store: ${store.storename}</h3>
				<table class="table-striped table table-condensed">
					<thead>
						<tr>
							<th>Image</th>
							<th>Product name</th>
							<th>Date Added</th>
							<th>Occasions</th>
							<th>Pick up</th>
							<th>Tracking</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="products" items="${productsStore.content}">
							<tr>
								<td><t:image product="${products }" clazz="table" /></td>
								<td>${products.name}</td>
								<td><fmt:formatDate type="date" value="${products.added}" /></td>

								<td><c:forEach var="ocassions"
										items="${products.occasions}">
										<p>${ocassions.name }</p>
										<br>
									</c:forEach></td>

								<td><c:choose>
										<c:when test="${products.pickup}">
											<i class="icon-ok"></i>
										</c:when>
										<c:otherwise>
											<i class="icon-remove"></i>
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${products.tracking}">
											<i class="icon-ok"></i>
										</c:when>
										<c:otherwise>
											<i class="icon-remove"></i>
										</c:otherwise>
									</c:choose></td>
								<td>$${products.price}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

			</div>
		</div>
		<div class="pagination pagination-right">
			<ul>
				<li <c:if test="${productsStore.firstPage}">class="disabled"</c:if>><a
					href="${pageContext.request.contextPath}/admin/stores/${store.id}/products?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${productsStore.number}">Prev</a></li>
				<li>
					<form method="get" class="form-inline inline">
						Page <input type="text" value="${productsStore.number+1}"
							name="page.page" class="input-mini" /> of
						${productsStore.totalPages} Sort by: <select name="page.sort"
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
							<option value="20" <c:if test="${20 == pageSize}">selected</c:if>>20</option>
							<option value="50" <c:if test="${50 == pageSize}">selected</c:if>>50</option>
							<option value="100"
								<c:if test="${100 == pageSize}">selected</c:if>>100</option>
						</select>
						<button type="submit" class="btn">Go</button>
					</form>
				</li>
				<li <c:if test="${productsStore.lastPage}">class="disabled"</c:if>><a
					href="${pageContext.request.contextPath}/admin/stores/${store.id}/products?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${productsStore.number+2}">Next</a></li>
			</ul>
		</div>
	</div>
	</div>

	</div>
</body>
</html>