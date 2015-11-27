<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />

<script type="text/javascript">
    
    function onSignIn(googleUser) {
    	  var profile = googleUser.getBasicProfile();
    	  
    	  localStorage.GoogleId = profile.getId();
    	  localStorage.Email = profile.getEmail();
    	  //$("body").data( "GoogleId", profile.getId() );
    	  //$("body").data( "Email", profile.getEmail() );
    	  
    	  var user = {
          		username: profile.getEmail()
          		};
          
          var json = JSON3.stringify(user);
          sendData(json);
    	}
    
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
			<h3>Sign in to Plaguester</h3>
			<p class="eum">Please enter your Google login below...</p>
			<div class="fig-text">
				<div class="g-signin2" data-onsuccess="onSignIn"></div>
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp" />