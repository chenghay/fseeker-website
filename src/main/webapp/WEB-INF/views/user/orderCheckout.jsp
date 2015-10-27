<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="../commonHeader.jsp" />
<title>FlowerSeeker check out preview</title>
</head>
<body>
	<c:import url="../topNavBar.jsp" />

	<div class="container">
		<div class="row">
			<div class="span3">
				<c:import url="sidebar.jsp" />
			</div>
			<div class="span9">
				<h3>Checkout</h3>
				<div class="alert alert-success">
					<table class="table-striped table table-condensed">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Price</th>
								<th>Quantity</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="productsCart" items="${productCart}">
								<tr>
									<td><t:image product="${productsCart.product }"
											clazz="table" /></td>
									<td>${productsCart.product.name}</td>
									<td>$${productsCart.price}</td>
									<td>${productsCart.quantity}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
					<p>
						<strong>Total: $${total }</strong>
					</p>

				</div>
				<hr>
				<h4>Shipping information</h4>

				<span class="text-error"><c:if test="${not empty errorMsg}">${errorMsg}<br>
					</c:if><br></span>
				<form:form method="post" commandName="addOrderForm"
					class="form-horizontal">

					<div class="control-group">
						<label class="control-label" for="address1">*Address 1</label>
						<div class="controls">
							<form:input path="address1" id="address1" />
							<form:errors path="address1" cssClass="text-error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="address2">Address 2</label>
						<div class="controls">
							<form:input path="address2" id="address2" />
							<form:errors path="address2" cssClass="text-error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="city">*City</label>
						<div class="controls">
							<form:input path="city" id="city" />
							<form:errors path="city" cssClass="text-error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="usState">*State</label>
						<div class="controls">
							<form:select path="usState" id="usState">
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
							<form:errors path="usState" cssClass="text-error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="zipCode">*Zipcode</label>
						<div class="controls">
							<form:input path="zipCode" id="zipCode" />
							<form:errors path="zipCode" cssClass="text-error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="instructions">Instructions</label>
						<div class="controls">
							<form:input path="instructions" id="instructions" />
							<form:errors path="instructions" cssClass="text-error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="pickup">Pickup</label>
						<div class="controls">
							<input type="checkbox" name="pickup" />

						</div>
					</div>
					<div class="form-actions">
						<button class="btn btn-primary" type="submit">Order now!</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>