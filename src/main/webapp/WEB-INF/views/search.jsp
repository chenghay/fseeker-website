<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="commonHeader.jsp" />
<title>FlowerSeeker</title>
</head>
<body>
	<c:import url="topNavBar.jsp" />

	<div class="container">
		<c:import url="userNavBar.jsp" />
		<h1>Results</h1>
		<div class="row">
		<div class="span12">
			<ul class="thumbnails">
				<c:forEach var="product" items="${products}">
					<li class="span4">
						<div class="thumbnail">
 
							<a
								href="${pageContext.request.contextPath}/products/${product.id}">
								<t:image product="${product }" clazz="reg"/>
							</a>
							
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>$${product.price}</p>
							<p>
								<c:forEach var="i" begin="1" end="${product.getAverageRank()}">
									<i class="icon-star"></i>
								</c:forEach>

								<c:forEach var="i" begin="${product.getAverageRank()}" end="4">
									<i class="icon-star-empty"></i>
								</c:forEach>
							</p>

						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		</div>
		<c:import url="footer.jsp" />
	</div>
</body>
</html>
