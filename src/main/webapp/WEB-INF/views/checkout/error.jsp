<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
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
            <div class="span8">
                <h1>FlowerSeeker</h1>
                <p>Your virtual flower market</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                    do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                    enim ad minim veniam, quis nostrud exercitation ullamco laboris
                    nisi ut.</p>
            </div>
            <div class="span4">
                <img src="${pageContext.request.contextPath}/static/images/bouquet.jpg" />
            </div>
        </div>

        <c:import url="../userNavBar.jsp"/>
    </div>

    <div class="container">
        <div class="row">
            <div class="span3">
                <div class="sidebar-nav">
                    <ul class="nav nav-list">
                        <li class="nav-header">Sidebar stuff?</li>
                        <li><a href="#">Sidebar links</a></li>
                    </ul>
                </div>
            </div>

            <div class="span9">
               
                    <h5>Checkout canceled or Error! (for paypal tracking id ${tracking})</h5>
                    <c:if test="${not empty errors}">
                    <c:forEach var="error" items="${errors}">
                     <p>${error.message }</p>
                    </c:forEach>
                    </c:if>
               
            </div>
        </div>
        <c:import url="../footer.jsp" />
    </div>
</body>
</html>
