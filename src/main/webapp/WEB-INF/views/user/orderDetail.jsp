<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
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
                <h3>Order Detail</h3>
    
                
                <table class="table-striped table table-condensed">
                    <thead>
                        <tr>   
                        	<th></th>     
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Review</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${order.orderEntries}">
                            <tr>
                            	<td><t:image product="${entry.product }" clazz="table"/></td>
                                <td><a
                                    href="${pageContext.request.contextPath}/products/${entry.product.id}">${entry.product.name}</a></td>
                                <td>${entry.quantity}</td>
                                <td>$${entry.price}</td>
                                <td><a class="btn" href="${pageContext.request.contextPath}/user/orders/${order.id }/products/${entry.product.id}/review">Review Product</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr/>
                <div>
                <p><strong>Confirmation ID: </strong>${order.groupId}</p>
                <p><strong>Date: </strong><fmt:formatDate type="date" value="${order.date}" /></p>
                
                <p><strong>Total: </strong>$${order.total }</p>
               
                <p><strong>Status: </strong>${order.status }</p>
                <p><strong>Destination: </strong></p>
                <address>
                            ${order.destination.address1}<br>
                            ${order.destination.city}, ${order.destination.state} ${order.destination.zip}<br>
                </address>
                <p><strong>Special Instructions:</strong></p>
                <div>
                ${order.instruction }
                </div>
                </div>
                <hr/>
                <h4>Store Information</h4>
                <address>
                        <strong>${order.store.storename}</strong><br>
                            ${order.store.location.address1}<br>
                            ${order.store.location.city}, ${order.store.location.state} ${order.store.location.zip}<br>
                        <strong>Email: </strong>${order.store.email }<br>
                        <strong>Phone: </strong>${order.store.phone }<br>
                    </address>
              
                
                   
            </div>
        </div>

    </div>
</body>
</html>