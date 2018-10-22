<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save batch</title>
	
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
			<h2>Save batch</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Batch</h3>
		
		<form:form action="saveBatch" modelAttribute="batch" method="POST">
		
		    <!-- need to associate this data with batch id -->
            <form:hidden path="id" />
		
			<table>
				<tbody>
					<tr>
						<td><label>Batch number:</label></td>
						<td><form:input path="batchNumber"/></td>
						<td><form:errors path="batchNumber" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Batch style:</label></td>
						<td><form:input path="batchStyle"/></td>
						<td><form:errors path="batchStyle" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Batch name:</label></td>
						<td><form:input path="batchName"/></td>
						<td><form:errors path="batchName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Batch creation date:</label></td>
						<td><form:input type="date" path="batchCreationDate"/></td>
						<td><form:errors path="batchCreationDate" cssClass="error" /></td>
					</tr>
					<!-- http://www.kscodes.com/spring-mvc/spring-mvc-select-tag-example/ -->
					<!-- http://websystique.com/springmvc/springmvc-hibernate-many-to-many-example-annotation-using-join-table/ -->
					<tr>
						<td><label>Malts:</label></td>
						<td>
						  	<form:select path="malts" multiple="true" itemValue="id" itemLabel="maltName" items="${maltList}">
						     	<form:option value="" />
						      	
                          	</form:select>
						</td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save"/></td>
					</tr>
				
					
				</tbody>
			</table>
			
		</form:form>
	
		<p>
			<a href="${pageContext.request.contextPath}/batch/list">Back to list</a>
			<br/>
			<a href="${pageContext.request.contextPath}/">Home</a>
		</p>
		
	</div>


</body>

</html>