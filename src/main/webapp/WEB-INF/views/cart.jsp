<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="commonHeader.jsp" />
<script type="text/javascript">
    function funcionUpdateCartde(id) {
        $.ajax({
            type : "GET",
            url : "${pageContext.request.contextPath}/cart/delete/" + id,
            success : function(response) {
                $("#cartContent").html(response);
            }
        });
    }
</script>
<title>FlowerSeeker</title>
</head>
<body>
	<c:import url="topNavBar.jsp" />
    <div class="container">
        <c:import url="userNavBar.jsp" />
		<h2>Cart</h2>
		<div id="cartContent">
			<c:import url="cartContents.jsp"/>
		</div>
	</div>

</body>
</html>