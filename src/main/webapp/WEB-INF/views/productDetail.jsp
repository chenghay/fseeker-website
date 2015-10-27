<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="commonHeader.jsp" />
<script type="text/javascript">
	function funcionUpdateCart() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/cart/add/${product.id}",
			success : function(response) {
				$("#subViewDiv").html(response);
			}
		});
	}

	function funcionUpdateCartde(id) {

		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/cart/delete/" + id,
			success : function(response) {
				$("#subViewDiv").html(response);
			}
		});
	}
</script>
<title>FlowerSeeker</title>
</head>
<body>
	<c:import url="topNavBar.jsp" />
	<div class="container">
		<c:import url="userNavBar.jsp" />
		<div class="row">
			<div class="span3">
				<c:import url="sidebarProduct.jsp" />
			</div>
			<div class="span9">
				<div>
					<h3>${product.name}</h3>
					<t:image product="${product }" clazz="reg"/>
					<p>
						<c:choose>
							<c:when test="${product.pickup}">
								<tr>
									<td><p>
											<b>You can pickup this order</b>
										</p></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><p>
											<b>Does not provide pick up order</b>
										</p></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</p>
					<p>
						<c:choose>
							<c:when test="${product.tracking}">
								<tr>
									<td><p>
											<b>Allows tracking order</b>
										</p></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><p>
											<b>Does not provide tracking order</b>
										</p></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</p>
					<hr />
					<button class="btn btn-success" type="button"
						onclick="funcionUpdateCart();">Add to Cart</button>
					<div id="subViewDiv"></div>
					<hr />
				</div>
				<div>
					<h4>Reviews:</h4>
					<c:forEach var="review" items="${reviews }">
						<div class="well">
							<p>
								<strong>Customer Name: </strong> ${review.customer.username}
							</p>
							<p>
								<strong>Date: </strong><fmt:formatDate type="date" value="${review.date}" />
							</p>
							<p>
								<strong>Rank: </strong>
								<c:forEach var="i" begin="1" end="${review.rank}" step="1">
									<i class="icon-star"></i>
								</c:forEach>

								<c:forEach var="i" begin="${review.rank}" end="4" step="1">
									<i class="icon-star-empty"></i>
								</c:forEach>
							</p>
							<p>
								<strong>Comment: </strong>${review.comment }
							</p>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
	</div>
</body>
</html>