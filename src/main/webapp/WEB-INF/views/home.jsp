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
		<h1>FlowerSeeker</h1>
		<div class="row">
			<div class="span5">
				<h4>Your virtual flower market</h4>
				<p>
					<br> <em> Have you ever heard of a flower that never
						needs water, soil, or sunshine? A flower that stays in full bloom
						through summer and winter, through good times and bad, a flower
						that rarely gets smaller, growing endlessly. A few lost petals, A
						little droop, but, without a doubt, it will always regrow into
						something more beautiful. I have one of those. </em>
				</p>
			</div>
			<div class="span7">
				<div id="myCarousel" class="carousel slide">
					<div class="carousel-inner">
						<div class="active item">
							<img class="centered"
								src="${pageContext.request.contextPath}/static/images/rosesindex2.jpg" />
							<div class="carousel-caption">
								<h4>Blue Pearl</h4>
								<p>
									Flower reference 1 <a href="#"> Read more...</a>
								</p>
							</div>
						</div>
						<div class="item">
							<img class="centered"
								src="${pageContext.request.contextPath}/static/images/rosesindex3.jpg">
							<div class="carousel-caption">
								<h4>Funny Grace</h4>
								<p>
									Flower reference 3<a href="#">Read more...</a>
								</p>
							</div>
						</div>
						<div class="item">
							<img class="centered"
								src="${pageContext.request.contextPath}/static/images/rosesindex4.jpg">
							<div class="carousel-caption">
								<h4>Red Woods</h4>
								<p>
									Flower reference 4 <a href="#"> Read more...</a>
								</p>
							</div>
						</div>
						<div class="item">
							<img class="centered"
								src="${pageContext.request.contextPath}/static/images/rosesindex5.jpg">
							<div class="carousel-caption">
								<h4>In the heart of the Rose</h4>
								<p>
									Flower reference 5 <a href="#"> Read more...</a>
								</p>
							</div>
						</div>
					</div>
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
						href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
			</div>
		</div>
		<c:import url="userNavBar.jsp" />
		<div class="row">
			<div class="span3">
				<div class="sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header"></li>
						 
					</ul>
				</div>
			</div>
			<div class="span9">
				<div class="row">
	
					<c:forEach var="products" items="${productPage.content}">
					<div class="span4">
						<h2>${products.name}</h2>						
						<a href="${pageContext.request.contextPath}/products/${products.id}"><t:image product="${products}" clazz="reg"/></a>
					</div>
					</c:forEach>
					
				</div>
			</div>
		</div>
		<c:import url="footer.jsp" />
	</div>
</body>
</html>