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
                <h3>Stores registered: ${store.totalElements}</h3>
                <table class="table-striped table table-condensed">
                    <thead>
                        <tr>
                            
                            <th>Name</th>
                            <th>Location</th>
                            <th>Phone</th>
                            <th>Number of products</th>
                            <th>Number of orders</th>
                            <th>Premium</th>
                            <th>Contact by Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="storesReg" items="${store.content}">
                            <tr>
                                 
                                <td><a href="${pageContext.request.contextPath}/admin/stores/${storesReg.id}">${storesReg.storename}</a></td>
                                <td>${storesReg.location.address1} - ${storesReg.location.state}</td>
                                <td>${storesReg.phone}</td>
                                <td><a href="${pageContext.request.contextPath}/admin/stores/${storesReg.id}/products">${fn:length(storesReg.products)}</a></td>
                                <td><a href="${pageContext.request.contextPath}/admin/stores/${storesReg.id}/orders">${fn:length(storesReg.orders)}</a></td>
                                <td><c:choose>
											<c:when test="${storesReg.subscribed }">
												<i class="icon-ok"></i>
											</c:when>
											<c:otherwise>
												<i class="icon-remove"></i>
											</c:otherwise>
										</c:choose></td>
                                <td><a href="${pageContext.request.contextPath}/admin/contactCustomer/${storesReg.id}">${storesReg.email}</a></td>
                                
                             </tr>
                        </c:forEach>
                        
                    </tbody>
                </table>
                
                <div class="pagination pagination-right">
                <ul>
                <li <c:if test="${store.firstPage}">class="disabled"</c:if>><a href="${pageContext.request.contextPath}/admin/stores?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${store.number}">Prev</a></li>
                <li>
                <form method="get" class="form-inline inline">
                    Page <input type="text" value="${store.number+1}" name="page.page" class="input-mini"/> of ${store.totalPages}
                    Sort by: <select name="page.sort" class="input-small">
                        <option value="storename" <c:if test="${'storename' == pageSort}">selected</c:if>>Name</option>
                    </select>
                    Dir: <select name="page.sort.dir" class="input-mini">
                        <option value="DESC" <c:if test="${'DESC' == pageSortDir}">selected</c:if>>DES</option>
                        <option value="ASC" <c:if test="${'ASC' == pageSortDir}">selected</c:if>>ASC</option>
                    </select>
                    Items per page: <select name="page.size" class="input-mini">
                        <option value="20" <c:if test="${20 == pageSize}">selected</c:if>>20</option>
                        <option value="50" <c:if test="${50 == pageSize}">selected</c:if>>50</option>
                        <option value="100" <c:if test="${100 == pageSize}">selected</c:if>>100</option>
                    </select>
                    <button type="submit" class="btn">Go</button>
                </form></li>
                <li <c:if test="${store.lastPage}">class="disabled"</c:if>><a href="${pageContext.request.contextPath}/admin/stores?page.sort=${pageSort}&page.sort.dir=${pageSortDir}&page.size=${pageSize}&page.page=${store.number+2}">Next</a></li>
                </ul>
                </div>
            </div>
        </div>

    </div>
</body>
</html>