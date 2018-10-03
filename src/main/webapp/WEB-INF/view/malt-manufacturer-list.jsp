<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Malt Manufacturer list</title>
</head>

<body>
	<div id="wrapper">
	
		<div id="header">
			<h2>Malt Manufacturer list</h2>		
		</div>
	
	</div>
	
	<div id="container">
	
		<!-- put new button: Add Country -->
		<input type="button" value="Add Malt Manufacturer"
			   onclick="window.location.href='showAddMaltManufacturerForm'; return false;"
		/>
		
		
		<!-- add html table here -->
		<table>
			<tr>
				<th>Name</th>
			</tr>
			
			<!-- loop over the malt manufacturer and print them -->
			<c:forEach var="tempMaltManufacturer" items="${maltManufacturers}">
			
				<!-- construct update link with malt id -->
				<c:url var="updateLink" value="/maltManufacturer/showMaltManufacturerUpdateForm">
					<c:param name="maltManufacturerId" value="${tempMaltManufacturer.id}"/>
				</c:url>
				
				<!--  construct delete link with malt id -->
				<c:url var="deleteLink" value="/maltManufacturer/delete">
					<c:param name="maltManufacturerId" value="${tempMaltManufacturer.id}"/>
				</c:url>
				
				<tr>
					<td>${tempMaltManufacturer.manufacturerName}</td>
					<td>
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
								   onclick="if (!(confirm('Are you sure you want to delete this manufacturer?'))) return false">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
			
		</table>
		
		<br>
		<a href="${pageContext.request.contextPath}/">Home</a>
		
	</div>
</body>

</html>