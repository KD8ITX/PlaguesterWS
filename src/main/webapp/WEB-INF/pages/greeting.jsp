<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8" />
  	<title>Plaguester - Home</title>
  	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"> 
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/json3/3.3.2/json3.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="906946387308-04o6t6hg6ceumfki11dlb80855e21je6.apps.googleusercontent.com">
    
    <style>
      #map-canvas {
        width: 500px;
        height: 400px;
      }
    </style>
    
    <script type="text/javascript">
    
    function onSignIn(googleUser) {
    	  var profile = googleUser.getBasicProfile();
    	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    	  console.log('Name: ' + profile.getName());
    	  console.log('Image URL: ' + profile.getImageUrl());
    	  console.log('Email: ' + profile.getEmail());
    	  
    	  $("body").data( "ID", profile.getId() );
    	  $("body").data( "Email", profile.getEmail() );
    	}
    
    function test() {
    	console.log('Did I hit this?');
    	console.log($("body").data("Email"));
    }
    
    window.onload = function(){
        
    }

    function handleGetCurrentPosition(location){
		var lat = location.coords.latitude;
		var lon = location.coords.longitude;
    	
        console.log('lat: ' + lat);
        console.log('long: ' + lon);
        
        
        var latlong = {
        		email: $("body").data("Email"),
        		lat: lat, 
        		lon: lon
        		};
        
        var json = JSON3.stringify(latlong);
        console.log('latlong: ' + json)
        sendData(json);
    }
    function onLocError(){}
    
    setInterval(
    		function()
    		{ 
    			if(navigator.geolocation) {
    	            navigator.geolocation.getCurrentPosition(handleGetCurrentPosition, onLocError);
    	        } 
    		}, 3000
    	);
    
    
    function sendData(data) {
    	console.log('sending ajax call...');
    	
    	$.ajax({
    		 type: "POST",
    		 url: "/Plaguester/V1/Geo/Update",
    		 data: data,
    		 contentType: "application/json; charset=utf-8",
    		 dataType: "json",
    		 success: function(msg) {
    		 	//alert('In Ajax');
    		 }
    		});
    	
    }
    </script>
    
    <script src="https://maps.googleapis.com/maps/api/js"></script>
    <script>
      function initialize() {
        var mapCanvas = document.getElementById('map-canvas');
        var mapOptions = {
          center: new google.maps.LatLng(44.5403, -78.5463),
          zoom: 8,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        var map = new google.maps.Map(mapCanvas, mapOptions)
        
        var myLatLong = new google.maps.LatLng(41.49932, -81.6943605);
        
        map.setCenter(myLatLong);
        map.setZoom(17);
        
        var marker = new google.maps.Marker({
            position: myLatLong,
            map: map,
            title: 'Starting point...'
        });
        
      }
      
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
    Hello ${name}
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
    
    <input type="button" name="click me" value="click me" onClick="test()" />
    
    <div id="map-canvas"></div>
</body>
</html>