<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                <h3> ${store.storename}</h3>
                <table class="table-striped table table-condensed">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Delivers to</th>
                            <th>Business hours</th>
                            <th>Location</th>
                            <th>Phone</th>
                            <th>Premium</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                            <tr>
                                <td>${store.storename}</td>
                                <td> 
	                                 <c:forEach var="deliverTo" items="${store.deliversto}">
	                                 <p>${deliverTo.city }, ${deliverTo.state } ${deliverTo.zip }</p>
	                                 </c:forEach>                                 
	                            </td>
                                <td> 
	                                 <c:forEach var="hours" items="${store.hours}">
	                                 <p>${hours.day } ${hours.fromtime } to ${hours.totime}</p>
	                               
	                                 </c:forEach>                                 
	                            </td>
                                <td>${store.location.address1} - ${store.location.state}</td>
                                <td>${store.phone}</td>
                                <td><c:choose>
											<c:when test="${store.subscribed }">
												<i class="icon-ok"></i>
											</c:when>
											<c:otherwise>
												<i class="icon-remove"></i>
											</c:otherwise>
										</c:choose></td>
                                <td>${store.email}</td>
                             </tr>
                        
                        
                    </tbody>
                </table>

            </div>
        </div>

    </div>
</body>
</html>