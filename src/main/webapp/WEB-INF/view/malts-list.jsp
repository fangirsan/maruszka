<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Malts list</title>
</head>

<body>
	<div id="wrapper">
	
		<div id="header">
			<h2>Malt list</h2>		
		</div>
	
	</div>
	
	<div id="container">
	
		<!-- put new button: Add Customer -->
		<input type="button" value="Add Malt"
			   onclick="window.location.href='showAddMaltForm'; return false;"
		/>
		
		
		<!-- add html table here -->
		<table>
			<tr>
				<th>Name</th>
				<th>Manufacturer</th>
				<th>Filling</th>
				<th>EBC</th>
				<th>Usage</th>
				<th>Action</th>
				
			</tr>
			
			<!-- loop over the mat and print them -->
			<c:forEach var="tempMalt" items="${malts}">
			
				<!-- construct update link with malt id -->
				<c:url var="updateLink" value="/malt/showMaltUpdateForm">
					<c:param name="maltId" value="${tempMalt.id}"/>
				</c:url>
				
				<!--  construct delete link with malt id -->
				<c:url var="deleteLink" value="/malt/delete">
					<c:param name="maltId" value="${tempMalt.id}"/>
				</c:url>
				
				<tr>
					<td>${tempMalt.maltName}</td>
					<td>${tempMalt.maltManufacturer}</td>
					<td>${tempMalt.maltFilling} %</td>
					<td>${tempMalt.maltEbc}</td>
					<td>${tempMalt.maltUsage}</td>
					<td>
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
								   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
			
		</table>
		
		<br>
		<a href="${pageContext.request.contextPath}/">Home</a>
		
	</div>
</body>

</html>