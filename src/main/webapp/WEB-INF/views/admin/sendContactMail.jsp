<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<html>
<head>
<c:import url="../commonHeader.jsp" />
<title>FlowerSeeker contact Florist</title>
</head>
<body>
     <c:import url="../topNavBar.jsp" />
    <div class="container">

        <div class="row">
            <div class="span3">
                <c:import url="sidebar.jsp" />
            </div>
            <div class="span9">
            <h3>Contact store by email: ${emailTo}</h3>
				<form:form method="post" commandName="sendEmailForm">
 
					<table class="table-striped table table-condensed">
			
					<tr>
						<td>Email recipient: </td>
						<td><form:input path="to"/></td>
						<td><span class="text-error"><form:errors path="to" /></span></td>
				     </tr>
				     <tr>
						<td>Email subject </td>
						<td><form:input path="subject"/></td>
						<td><span class="text-error"><form:errors path="subject" /></span></td>
				     </tr>
				     
				     <tr>
						<td><p>Message</td>
						<td><form:textarea path="message"/></td>
						<td><span class="text-error"><form:errors path="message" /></span></td>
				     </tr> 
				   
				     </table>  
				 
				 
				    <button class="btn btn-primary" type="submit">Send Email</button>
				    
				 
				</form:form>
            </div>
        </div>

    </div>
 
</body>
</html>