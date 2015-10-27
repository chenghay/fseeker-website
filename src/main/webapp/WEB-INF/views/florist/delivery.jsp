<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <h3>Delivery Areas:</h3>
                <table class="table-striped table table-condensed">
                    <thead>
                        <tr>
                            <th>City</th>
                            <th>State</th>
                            <th>Zip</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="area" items="${areas}">
                            <tr>
                                <td>${area.city }</td>
                                <td>${area.state}</td>
                                <td>${area.zip}</td>
                                <td><form method="post" class="form-inline inline" action="${pageContext.request.contextPath}/florist/delivery/delete"><input type="hidden" name="deliveryId" value="${area.id}"/><button class="btn" type="submit">Remove</button> </form></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h4>Add Delivery Area</h4>
                <form:form method="post" action="${pageContext.request.contextPath}/florist/delivery/add" commandName="addDeliveryAreaForm" class="form-inline">
                    City: <form:input path="city"/>
                    State: <form:select path="state" class="input-mini">
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
                    Zip: <form:input path="zipcode" class="input-small"/>   
                    <button class="btn btn-primary" type="submit">Add</button>
                    <div class="text-error"><form:errors path="city" /></div>
                    <div class="text-error"><form:errors path="state" /></div>
                    <div class="text-error"><form:errors path="zipcode" /></div>
                   
                </form:form>
            </div>
        </div>

    </div>
</body>
</html>