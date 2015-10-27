<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="commonHeader.jsp"/>
<title>FlowerSeeker</title>
</head>
<body onload='document.f.j_username.focus();'>
    <c:import url="topNavBar.jsp"/>
    
<div class="container">
<h3>Login</h3>
<form name='f' action='${pageContext.request.contextPath}/processlogin' method='POST'>
    <label>Username</label>
    <input type="text" name="j_username" placeholder="username"/>
    <label>Password</label>
    <input type="password" name="j_password" placeholder="password"/><br/>
    <button type="submit" class="btn btn-primary">Login</button>
    <a href="${pageContext.request.contextPath}/florist/register" class="btn btn-link">Register (Florist)</a>
    <a href="${pageContext.request.contextPath}/user/register" class="btn btn-link">Register (Customer)</a>
</form>
<c:if test="${loginError }">
    <div class="alert alert-error">Login Error: wrong password</div>
</c:if>
</div>
</body></html>