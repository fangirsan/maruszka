<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save malt manufacturer</title>
	
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
			<h2>Save malt manufacturer</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Malt Manufacturer</h3>
		
		<form:form action="saveMaltManufacturer" modelAttribute="maltManufacturer" method="POST">
		
		    <!-- need to associate this data with malt manufacturer id -->
            <form:hidden path="id" />
		
			<table>
				<tbody>
					<tr>
						<td><label>Manufacturer name:</label></td>
						<td><form:input path="manufacturerName"/></td>
						<td><form:errors path="manufacturerName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				</tbody>
			</table>
			
		</form:form>
	
		<p>
			<a href="${pageContext.request.contextPath}/maltManufacturer/list">Back to list</a>
			<br/>
			<a href="${pageContext.request.contextPath}/">Home</a>
		</p>
		
	</div>


</body>

</html>