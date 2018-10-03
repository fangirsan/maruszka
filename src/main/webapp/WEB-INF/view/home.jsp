<!-- % response.sendRedirect("malt/list"); %-->

<html>
<head>
	<title>Maruszka Brewery Home Page</title>
</head>
<body>
	
	<div style="height:100%; width:100%; background-color:LightGray">
		 
		<a href="${pageContext.request.contextPath}/malt/list">Malt list</a>
		
		<br>
		
		<a href="${pageContext.request.contextPath}/hop/list">Hop list</a>
		
		<br>
        
        <a href="${pageContext.request.contextPath}/country/list">Country list</a>
        
        <br>
        
        <a href="${pageContext.request.contextPath}/batch/list">Batch list</a>
        
        <br>
        
        <a href="${pageContext.request.contextPath}/maltManufacturer/list">Malt Manufacturer list</a>
	
	</div>


</body>
</html>