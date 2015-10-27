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
                <h3>Pickup hours:</h3>
                <table class="table-striped table table-condensed">
                    <thead>
                        <tr>
                            <th>Day</th>
                            <th>From</th>
                            <th>To</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="hour" items="${store.hours}">
                            <tr>
                                <td>${hour.day }</td>
                                <td>${hour.fromtime}</td>
                                <td>${hour.totime}</td>
                                <td><form method="post" class="form-inline inline" action="${pageContext.request.contextPath}/florist/hours/delete"><input type="hidden" name="hourId" value="${hour.id}"/><button class="btn" type="submit">Remove</button> </form></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h4>Add Hour</h4>
                <form:form method="post" action="${pageContext.request.contextPath}/florist/hours/add" commandName="addHourForm" class="form-inline">
                    Day: <form:select path="day" class="input-small">
                         <option value="MON">MON</option>
                         <option value="TUE">TUE</option>
                         <option value="WED">WED</option>
                         <option value="THU">THU</option>
                         <option value="FRI">FRI</option>
                         <option value="SAT">SAT</option>
                         <option value="SUN">SUN</option>
                    </form:select>
                    From: <form:input class="input-mini" path="from"/>
                    <form:select path="fromhalf" class="input-mini"><option value="AM">AM</option><option value="PM">PM</option></form:select>
                    To: <form:input class="input-mini" path="to"/>
                    <form:select path="tohalf" class="input-mini"><option value="AM">AM</option><option value="PM">PM</option></form:select>
                    <button class="btn btn-primary" type="submit">Add</button>
                    <div class="text-error"><form:errors path="from" /></div>
                    <div class="text-error"><form:errors path="to" /></div>
                    <form:errors/>
                </form:form>
            </div>
        </div>

    </div>
</body>
</html>