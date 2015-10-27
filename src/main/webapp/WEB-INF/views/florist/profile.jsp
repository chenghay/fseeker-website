<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<h3>Profile for ${store.storename } <small><c:if test="${store.subscribed }">(Subscribed)</c:if></small></h3>
				<div>
				    <address>
                        <strong>${store.storename}</strong><br>
                            ${store.location.address1}<br>
                            ${store.location.city}, ${store.location.state} ${store.location.zip}<br>
                        <strong>Email: </strong>${store.email }<br>
                        <strong>Phone: </strong>${store.phone }<br>
                    </address>
                
				</div>
				<h4>Recent Orders: <small><a href="${pageContext.request.contextPath}/florist/orders">More</a></small></h4>
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
                </c:if>
				<h4>Recent Reviews: <small><a href="${pageContext.request.contextPath}/florist/reviews">More</a></small></h4>
				<c:import url="snippets/reviews.jsp" />
			</div>
		</div>

	</div>
</body>
</html>