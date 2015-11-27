<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />

<script type="text/javascript">
    
    function sendData(data) {
    	console.log('sending ajax call...');
    	
    	$.ajax({
    		 type: "POST",
    		 url: "V1/User/Login",
    		 data: data,
    		 contentType: "application/json; charset=utf-8",
    		 dataType: "json",
    		 success: function(msg) {
    			 localStorage.Id = msg.userId;
    			 //$("body").data( "Id", msg.id );
    			 window.location.href = "map";
    		 }
    		});
    	
    }
</script>
</head>
<body>
<jsp:include page="links.jsp" />

<div class="about">
		<div class="container">
			<h3>Manage your family</h3>
			<p class="eum">Click to <a href="family/add">add member</a></p>
			<div class="fig-text">
			
			   
			<table style="padding: 15px;" border="1">
			<c:forEach items="${people}" var="people">
				<tr>
				<td><c:out value="${people.name}"/></td>
				<td><c:out value="${people.gender}"/></td>
				</tr>
			</c:forEach>
			</table>  
			
				
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp" />