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
<script type="text/javascript">
$(document).ready(function(){
	//put everything in this document ready function
	$('#customerUsername').blur(function() {
		var name = $(this).val();
		$.getJSON('${pageContext.request.contextPath}/florist/ajax/' + name + '/available', function(data) {
			if (data) {
			    $('#usernameError').html('');
			} else {
				$('#usernameError').html('Username not available');
			}
		});
	});
});
</script>
<title>FlowerSeeker</title>
</head>
<body>
	<c:import url="../topNavBar.jsp" />

	<div class="container">
		<h3>Customer Registration</h3>
		<span class="text-error"><c:if test="${not empty errorMsg}">${errorMsg}<br>
			</c:if><br></span>

		<form:form method="post" commandName="customerRegistrationForm" class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="customerFirstName">*First Name</label>
				<div class="controls">
					<form:input path="customerFirstName" id="customerFirstName" />
					<span class="text-error"><form:errors
							path="customerFirstName" /></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerLastName">*Last Name</label>
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
				<label class="control-label" for="customerEmail">*Email</label>
				<div class="controls">
					<form:input path="customerEmail" id="customerEmail" />
					<span class="text-error"><form:errors path="customerEmail" /></span>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerUsername">*Username</label>
				<div class="controls">
					<form:input path="customerUsername" id="customerUsername" />
					<span id="usernameError" class="text-error"><form:errors
							path="customerUsername" /> <c:if test="${not empty errorMsg}">${errorMsg}</c:if>
					</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerPassword">*Password</label>
				<div class="controls">
					<form:password path="customerPassword" id="customerPassword" />
					<span class="text-error"><form:errors
							path="customerPassword" /></span>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerRepeatPassword">*Confirm
					Password</label>
				<div class="controls">
					<form:password path="customerRepeatPassword"
						id="customerRepeatPassword" />
					<span class="text-error"><form:errors path="passwordMatch" /></span>

				</div>
			</div>

			<div class="form-actions">
				<button class="btn btn-primary" type="submit">Register</button>
			</div>
		</form:form>
	</div>
</body>
</html>