<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../commonHeader.jsp" />
<script type="text/javascript">

// example of using jquery to use ajax to check if username is available
// on losing focus of the username input, makes a get call to url and checks if available
$(document).ready(function(){
	//put everything in this document ready function
	$('#usernameInput').blur(function() {
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
		<h3>Store Creation</h3>
		<form:form method="post" commandName="floristRegistrationForm"
			class="form-horizontal">
			<spring:hasBindErrors name="floristRegistrationForm">
			<div class="alert alert-error">There are errors in your input!</div></spring:hasBindErrors>
			<fieldset>
				<legend>Store Information (How customers will contact you)</legend>
				<div class="control-group">
					<label class="control-label" for="storename">*Name</label>
					<div class="controls">
						<form:input path="storename" id="storename" />
						<form:errors path="storename" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address1">*Address 1</label>
					<div class="controls">
						<form:input path="address1" id="address1" />
						<form:errors path="address1" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address2">Address 2</label>
					<div class="controls">
						<form:input path="address2" id="address2" />
						<form:errors path="address2" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="city">*City</label>
					<div class="controls">
						<form:input path="city" id="city" />
						<form:errors path="city" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="state">*State</label>
					<div class="controls">
						<form:select path="state" id="state">
							<form:option value="">Select a State</form:option>
							<form:option value="AL">Alabama</form:option>
							<form:option value="AK">Alaska</form:option>
							<form:option value="AZ">Arizona</form:option>
							<form:option value="AR">Arkansas</form:option>
							<form:option value="CA">California</form:option>
							<form:option value="CO">Colorado</form:option>
							<form:option value="CT">Connecticut</form:option>
							<form:option value="DE">Delaware</form:option>
							<form:option value="DC">District Of Columbia</form:option>
							<form:option value="FL">Florida</form:option>
							<form:option value="GA">Georgia</form:option>
							<form:option value="HI">Hawaii</form:option>
							<form:option value="ID">Idaho</form:option>
							<form:option value="IL">Illinois</form:option>
							<form:option value="IN">Indiana</form:option>
							<form:option value="IA">Iowa</form:option>
							<form:option value="KS">Kansas</form:option>
							<form:option value="KY">Kentucky</form:option>
							<form:option value="LA">Louisiana</form:option>
							<form:option value="ME">Maine</form:option>
							<form:option value="MD">Maryland</form:option>
							<form:option value="MA">Massachusetts</form:option>
							<form:option value="MI">Michigan</form:option>
							<form:option value="MN">Minnesota</form:option>
							<form:option value="MS">Mississippi</form:option>
							<form:option value="MO">Missouri</form:option>
							<form:option value="MT">Montana</form:option>
							<form:option value="NE">Nebraska</form:option>
							<form:option value="NV">Nevada</form:option>
							<form:option value="NH">New Hampshire</form:option>
							<form:option value="NJ">New Jersey</form:option>
							<form:option value="NM">New Mexico</form:option>
							<form:option value="NY">New York</form:option>
							<form:option value="NC">North Carolina</form:option>
							<form:option value="ND">North Dakota</form:option>
							<form:option value="OH">Ohio</form:option>
							<form:option value="OK">Oklahoma</form:option>
							<form:option value="OR">Oregon</form:option>
							<form:option value="PA">Pennsylvania</form:option>
							<form:option value="RI">Rhode Island</form:option>
							<form:option value="SC">South Carolina</form:option>
							<form:option value="SD">South Dakota</form:option>
							<form:option value="TN">Tennessee</form:option>
							<form:option value="TX">Texas</form:option>
							<form:option value="UT">Utah</form:option>
							<form:option value="VT">Vermont</form:option>
							<form:option value="VA">Virginia</form:option>
							<form:option value="WA">Washington</form:option>
							<form:option value="WV">West Virginia</form:option>
							<form:option value="WI">Wisconsin</form:option>
							<form:option value="WY">Wyoming</form:option>
						</form:select>
						<form:errors path="state" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="zipcode">*Zipcode</label>
					<div class="controls">
						<form:input path="zipcode" id="zipcode" />
						<form:errors path="zipcode" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="storeEmail">*Email</label>
					<div class="controls">
						<form:input path="storeEmail" id="storeEmail" />
						<form:errors path="storeEmail" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="paypal">*Paypal Email</label>
					<div class="controls">
						<form:input path="paypal" id="paypal" />
						<form:errors path="paypal" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="phone">*Phone
						(xxx-xxx-xxxx)</label>
					<div class="controls">
						<form:input path="phone" id="phone" />
						<form:errors path="phone" cssClass="text-error"/>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>Account Information (How Flowerseeker will contact you)</legend>
				<div class="control-group">
					<label class="control-label" for="name">*First Name</label>
					<div class="controls">
						<form:input path="firstname" id="firstname" />
						<form:errors path="firstname" cssClass="text-error"/>
					</div>
				</div>
				<div class="control-group">
                    <label class="control-label" for="name">*Last Name</label>
                    <div class="controls">
                        <form:input path="lastname" id="lastname" />
                        <form:errors path="lastname" cssClass="text-error"/>
                    </div>
                </div>
				<div class="control-group">
					<label class="control-label" for="usernameInput">*Username</label>
					<div class="controls">
						<form:input path="username" id="usernameInput" />
						<span id="usernameError" class="text-error"><form:errors
								path="username" /> <c:if test="${not empty errorMsg}">${errorMsg}</c:if>
						</span>
					</div>
				</div>
				<div class="control-group">
                    <label class="control-label" for="userphone">*Phone (xxx-xxx-xxxx)</label>
                    <div class="controls">
                        <form:input path="userphone" id="userphone" />
                        <form:errors path="userphone" cssClass="text-error" />

                    </div>
                </div>

				<div class="control-group">
					<label class="control-label" for="email">*Email</label>
					<div class="controls">
						<form:input path="email" id="email" />
						<form:errors path="email" cssClass="text-error"/>

					</div>
				</div>


				<div class="control-group">
					<label class="control-label" for="password">*Password</label>
					<div class="controls">
						<form:password path="password" id="password" />
						<form:errors path="password" cssClass="text-error" />

					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="confirmPassword">*Confirm
						Password</label>
					<div class="controls">
						<form:password path="confirmPassword" id="confirmPassword" />
						<form:errors path="passwordMatch" cssClass="text-error" />

					</div>
				</div>
			</fieldset>
			<div class="form-actions">
			<button class="btn btn-primary" type="submit">Register</button>
			</div>
		</form:form>
	</div>
</body>
</html>