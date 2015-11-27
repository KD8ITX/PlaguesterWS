<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />

<style>
      #map-canvas {
        width: 500px;
        height: 400px;
      }
</style>

<script type="text/javascript">
	function handleGetCurrentPosition(location){
		var lat = location.coords.latitude;
		var lon = location.coords.longitude;
    	
        console.log('lat: ' + lat);
        console.log('long: ' + lon);
        
        
        var latlong = {
        		email: localStorage.Id,
        		lat: lat, 
        		lon: lon
        		};
        
        var json = JSON3.stringify(latlong);
        console.log('latlong: ' + json)
        
        // Log where we are...
        document.getElementById('log').innerHTML = "Lat: " + lat + ", Long: " + lon;
        
		var myNewLatLong = new google.maps.LatLng(lat, lon);
        map.setCenter(myNewLatLong);
        map.setZoom(18);
        
        var marker = new google.maps.Marker({
            position: myNewLatLong,
            map: map,
            title: 'Starting point...'
        });
        
        // Save to backend
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
    		 headers: { 
    	        'Accept': 'application/json'
    	     },
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
    var map;
      function initialize() {
        var mapCanvas = document.getElementById('map-canvas');
        var mapOptions = {
          zoom: 18,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        map = new google.maps.Map(mapCanvas, mapOptions)
        
      }
      
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<jsp:include page="links.jsp" />

<div class="about">
		<div class="container">
			<h3>Please find yourself...</h3>
			<p class="eum">What may ye find below...</p>
			<div class="fig-text">
				<div id="map-canvas"></div>
				<div id="log">initial content</div>
				
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp" />