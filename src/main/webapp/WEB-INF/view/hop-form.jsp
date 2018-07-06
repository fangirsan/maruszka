<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save hop</title>
	
	<!-- link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" -->
		  
	<!-- link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" -->
		  
		  <style>
		  	.error {color:red}
		  </style>
		  
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Save hop</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Hop</h3>
		
		<form:form action="saveHop" modelAttribute="hop" method="POST">
		
			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Hop name:</label></td>
						<td><form:input path="hopName"/></td>
						<td><form:errors path="hopName" cssClass="error" /></td>
						
					</tr>
					<tr>
						<td><label>&alpha; acid min:</label></td>
						<td><form:input path="alphaAcidMin"/></td>
						<td><form:errors path="alphaAcidMin" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>&alpha; acid max:</label></td>
						<td><form:input path="alphaAcidMax"/></td>
						<td><form:errors path="alphaAcidMax" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Taste:</label></td>
						<td>
						  Yes <form:radiobutton path="hopTaste" value="Yes" />
                          No <form:radiobutton path="hopTaste" value="No" />
						</td>
						<td><form:errors path="hopTaste" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Aroma:</label></td>
						<td>
						    Yes <form:radiobutton path="hopAroma" value="Yes" />
                            No <form:radiobutton path="hopAroma" value="No" />
                        </td>
                        <td><form:errors path="hopAroma" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Origin:</label></td>
						<td>
						  <form:select path="hopOrigin" multiple="false">
						      <form:option value="" label="...." />
						      <form:options items="${countryList}"/>
                          </form:select>
						 </td>
						 <td><form:errors path="hopAroma" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				</tbody>
			</table>
			
		</form:form>
	
		<p>
			<a href="${pageContext.request.contextPath}/hop/list">Back to list</a>
			<br/>
			<a href="${pageContext.request.contextPath}/">Home</a>
		</p>
		
	</div>


</body>

</html>