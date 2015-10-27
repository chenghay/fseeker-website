<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <h3>Subscription Information:</h3>
                <c:choose>
                    <c:when test="${store.subscribed}">
                    <div>
                        <p>You're currently subscribed!</p>
                        <form method="post"><input type="hidden" name="trial" value="false"/><input type="hidden" name="action" value="remove"/><button type="submit" class="btn">Remove membership</button></form>
                    </div>
                    </c:when>
                    <c:otherwise>
                    <div>You're not currently subscribed to premium membership.</div>
                    <c:if test="${trialOk }">
                        <div><form method="post"><input type="hidden" name="trial" value="true"/><input type="hidden" name="action" value="add"/><button type="submit" class="btn">Start 3 month trial membership</button></form></div>
                    </c:if>
                    <div><form method="post"><input type="hidden" name="trial" value="false"/><input type="hidden" name="action" value="add"/><button type="submit" class="btn">Start 1 year membership (you will be redirected to Paypal)</button></form></div>
                    </c:otherwise>
                </c:choose>
                <h4>Current and past subscriptions:</h4>
                <table class="table-striped table table-condensed">
                    <thead>
                        <tr>
                            <th>Start</th>
                            <th>Expiration</th>
                            <th>Trial</th>
                            <th>Active</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sub" items="${subscriptions}">
                            <tr>
                                <td><fmt:formatDate type="date" value="${sub.start }"/></td>
                                <td><fmt:formatDate type="date" value="${sub.expiration}"/></td>
                                <td><c:choose><c:when test="${sub.trial }"><i class="icon-ok"></i></c:when><c:otherwise><i class="icon-remove"></i></c:otherwise></c:choose></td>
                                <td><c:choose><c:when test="${sub.active}"><i class="icon-ok"></i></c:when><c:otherwise><i class="icon-remove"></i></c:otherwise></c:choose></td>
                                <td>${sub.status }</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</body>
</html>