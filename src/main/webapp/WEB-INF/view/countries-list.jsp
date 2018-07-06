<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Country list</title>
</head>

<body>
	<div id="wrapper">
	
		<div id="header">
			<h2>Country list</h2>		
		</div>
	
	</div>
	
	<div id="container">
	
		<!-- put new button: Add Country -->
		<input type="button" value="Add Country"
			   onclick="window.location.href='showAddCountryForm'; return false;"
		/>
		
		
		<!-- add html table here -->
		<table>
			<tr>
				<th>Name</th>
			</tr>
			
			<!-- loop over the country and print them -->
			<c:forEach var="tempCountry" items="${countries}">
			
				<!-- construct update link with malt id -->
				<c:url var="updateLink" value="/country/showCountryUpdateForm">
					<c:param name="countryId" value="${tempCountry.id}"/>
				</c:url>
				
				<!--  construct delete link with malt id -->
				<c:url var="deleteLink" value="/country/delete">
					<c:param name="countryId" value="${tempCountry.id}"/>
				</c:url>
				
				<tr>
					<td>${tempCountry.countryName}</td>
					<td>
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
								   onclick="if (!(confirm('Are you sure you want to delete this country?'))) return false">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
			
		</table>
		
		<br>
		<a href="${pageContext.request.contextPath}/">Home</a>
		
	</div>
</body>

</html>