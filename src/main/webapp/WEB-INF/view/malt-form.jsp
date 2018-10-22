<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save malt</title>
	
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
			<h2>Save malt</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Malt</h3>
		
		<form:form action="saveMalt" modelAttribute="malt" method="POST">
		
		    <!-- need to associate this data with malt manufacturer list id -->
            <form:hidden path="id" />
		
			<table>
				<tbody>
					<tr>
						<td><label>Malt name:</label></td>
						<td><form:input path="maltName"/></td>
						<td><form:errors path="maltName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Manufacturer:</label></td>
						<!-- td><form:input path="maltManufacturer"/></td-->
						<td>
						  <form:select path="maltManufacturer" multiple="false">
						      <form:option value="" label="...." />
						      <form:options items="${maltManufacturerList}"/>
                          </form:select>
						</td>
					</tr>
					<tr>
						<td><label>Filling:</label></td>
						<td><form:input path="maltFilling"/></td>
					</tr>
					<tr>
						<td><label>EBC:</label></td>
						<td><form:input path="maltEbc"/></td>
					</tr>
					<tr>
						<td><label>Usage:</label></td>
						<td><form:input path="maltUsage"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				</tbody>
			</table>
			
		</form:form>
	
		<p>
			<a href="${pageContext.request.contextPath}/malt/list">Back to list</a>
			<br/>
			<a href="${pageContext.request.contextPath}/">Home</a>
		</p>
		
	</div>


</body>

</html>