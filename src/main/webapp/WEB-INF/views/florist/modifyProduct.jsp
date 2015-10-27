<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../commonHeader.jsp" />
<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    //put everything in this document ready function
    $('#occasions').multiselect();
});
</script>
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

				<h3>Modify Product</h3>
					
				<form:form method="post" enctype="multipart/form-data"
					commandName="modifyProductForm" class="form-horizontal">
					<spring:hasBindErrors name="modifyProductForm">
					<div class="alert alert-error">There are errors in your input!</div></spring:hasBindErrors>
					<t:image product="${product}" clazz="reg"/> 
				
					<fieldset><legend>Product Information</legend>
					<div class="control-group">
						<label class="control-label" for="name">*Product Name</label>
						<div class="controls">
							<form:input path="name" id="name" />
							<form:errors path="name" cssClass="text-error"/>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="description">*Description</label>
						<div class="controls">
							<form:textarea path="description" id="description" />
							<form:errors path="description" cssClass="text-error"/>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="price">*Price</label>
						<div class="controls">
							<form:input path="price" id="price" />
							<form:errors path="price" cssClass="text-error"/>

						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"> <form:checkbox path="available" />
								Available
							</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="file">Image</label>
						<div class="controls">
							<form:input path="file" type="file" id="file" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="occasions">*Occasions</label>
						<div class="controls">
							<form:select multiple="multiple" path="occasions" id="occasions">
								<form:options items="${occasions}" itemValue="name"
									itemLabel="name" />
							</form:select>
                            <form:errors path="occasions" cssClass="text-error"/>
						</div>
					</div>
					</fieldset>
                    <c:if test="${store.subscribed}">
                    <fieldset><legend>Premium Options</legend>
                    <div class="control-group">
                        <div class="controls">
                            <label class="checkbox"> <form:checkbox path="tracking" />Tracking</label>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label class="checkbox"> <form:checkbox path="pickup" />Local Pickup</label>
                        </div>
                    </div>
                    </fieldset>
                    </c:if>


					<form:hidden path="id" />
					<div class="form-actions">
					<button class="btn btn-primary" type="submit">Modify</button>
					<a class="btn"
                        href="${pageContext.request.contextPath}/florist/products/${modifyProductForm.id}">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>