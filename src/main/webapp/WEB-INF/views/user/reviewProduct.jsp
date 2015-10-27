<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
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

				<h3>Rank and Review Product - ${product.name }</h3>
				<t:image product="${product }" clazz="reg"/>
					
				<hr/>
				<c:choose>
				<c:when test="${not empty review }">
				<div>
				<h4>You already reviewed this product!</h4>
				<p>Rank: ${review.rank }</p>
				<div>Comment: ${review.comment }</div>
				</div>
				</c:when>
				<c:otherwise>
				<form:form method="post"
					commandName="addReviewForm" class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="comment">*Comment</label>
						<div class="controls">
							<form:textarea path="comment" id="comment" />
							<span class="text-error"><form:errors path="comment" /></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="rank">*Rank (5 is highest)</label>
						<div class="controls">
							<form:select path="rank">
							<form:option value="" >Select rank</form:option>
							<form:option value="1">1</form:option>
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
							<form:option value="4">4</form:option>
							<form:option value="5">5</form:option>
						</form:select>
							<span class="text-error"><form:errors path="rank" /></span>

						</div>
					</div>
				<form:hidden path="username"/>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit">Review</button>
					<a class="btn"
                        href="${pageContext.request.contextPath}/user/orders/${orderid}">Cancel</a>
					</div>
							     
				</form:form>		
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>