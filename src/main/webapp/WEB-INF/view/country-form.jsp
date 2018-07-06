<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save country</title>
	
	<!-- link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" -->
		  
	<!-- link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" -->
		  
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Save country</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Country</h3>
		
		<form:form action="saveCountry" modelAttribute="country" method="POST">
		
		    <!-- need to associate this data with country id -->
            <form:hidden path="id" />
		
			<table>
				<tbody>
					<tr>
						<td><label>Country name:</label></td>
						<td><form:input path="countryName"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				</tbody>
			</table>
			
		</form:form>
	
		<p>
			<a href="${pageContext.request.contextPath}/country/list">Back to list</a>
			<br/>
			<a href="${pageContext.request.contextPath}/">Home</a>
		</p>
		
	</div>


</body>

</html>