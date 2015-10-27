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
				<h3>Update Profile</h3>

				<span class="text-error"><c:if test="${not empty errorMsg}">${errorMsg}<br>
					</c:if><br></span>

				<form:form method="post"
					commandName="customerModifyRegistrationForm"
					class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="customerFirstName">*First
							Name</label>
						<div class="controls">
							<form:input path="customerFirstName" id="customerFirstName" />
							<span class="text-error"><form:errors
									path="customerFirstName" /></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="customerLastName">*Last
							Name</label>
						<div class="controls">
							<form:input path="customerLastName" id="customerLastName" />
							<span class="text-error"><form:errors
									path="customerLastName" /></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="customerPhonenumber">*Phone
							(xxx-xxx-xxxx)</label>
						<div class="controls">
							<form:input path="customerPhonenumber" id="customerPhonenumber" />
							<span class="text-error"><form:errors
									path="customerPhonenumber" /></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">*Email</label>
						<div class="controls">
							<form:input path="email" id="email" />
							<span class="text-error"><form:errors path="email" /></span>
						</div>
					</div>
					<form:hidden path="id"/>
					<div class="form-actions">
						<button class="btn btn-primary" type="submit">Update
							Profile</button>
						<a class="btn"
							href="${pageContext.request.contextPath}/user/profile">Cancel</a>
					</div>
				</form:form>

			</div>
		</div>
	</div>
</body>
</html>