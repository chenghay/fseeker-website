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
				<h3>Update Store Information</h3>
				<form:form method="post" commandName="modifyStoreForm"
					class="form-horizontal">
					<spring:hasBindErrors name="modifyStoreForm">
					<div class="alert alert-error">There are errors in your input!</div></spring:hasBindErrors>
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
							<form:option value="AL">AL</form:option>
                            <form:option value="AK">AK</form:option>
                            <form:option value="AZ">AZ</form:option>
                            <form:option value="AR">AR</form:option>
                            <form:option value="CA">CA</form:option>
                            <form:option value="CO">CO</form:option>
                            <form:option value="CT">CT</form:option>
                            <form:option value="DE">DE</form:option>
                            <form:option value="DC">DC</form:option>
                            <form:option value="FL">FL</form:option>
                            <form:option value="GA">GA</form:option>
                            <form:option value="HI">HI</form:option>
                            <form:option value="ID">ID</form:option>
                            <form:option value="IL">IL</form:option>
                            <form:option value="IN">IN</form:option>
                            <form:option value="IA">IA</form:option>
                            <form:option value="KS">KS</form:option>
                            <form:option value="KY">KY</form:option>
                            <form:option value="LA">LA</form:option>
                            <form:option value="ME">ME</form:option>
                            <form:option value="MD">MD</form:option>
                            <form:option value="MA">MA</form:option>
                            <form:option value="MI">MI</form:option>
                            <form:option value="MN">MN</form:option>
                            <form:option value="MS">MS</form:option>
                            <form:option value="MO">MO</form:option>
                            <form:option value="MT">MT</form:option>
                            <form:option value="NE">NE</form:option>
                            <form:option value="NV">NV</form:option>
                            <form:option value="NH">NH</form:option>
                            <form:option value="NJ">NJ</form:option>
                            <form:option value="NM">NM</form:option>
                            <form:option value="NY">NY</form:option>
                            <form:option value="NC">NC</form:option>
                            <form:option value="ND">ND</form:option>
                            <form:option value="OH">OH</form:option>
                            <form:option value="OK">OK</form:option>
                            <form:option value="OR">OR</form:option>
                            <form:option value="PA">PA</form:option>
                            <form:option value="RI">RI</form:option>
                            <form:option value="SC">SC</form:option>
                            <form:option value="SD">SD</form:option>
                            <form:option value="TN">TN</form:option>
                            <form:option value="TX">TX</form:option>
                            <form:option value="UT">UT</form:option>
                            <form:option value="VT">VT</form:option>
                            <form:option value="VA">VA</form:option>
                            <form:option value="WA">WA</form:option>
                            <form:option value="WV">WV</form:option>
                            <form:option value="WI">WI</form:option>
                            <form:option value="WY">WY</form:option>
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
					<form:hidden path="id" />
					<div class="form-actions">
					<button class="btn btn-primary" type="submit">Modify</button>
					<a class="btn"
                        href="${pageContext.request.contextPath}/florist/profile">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>