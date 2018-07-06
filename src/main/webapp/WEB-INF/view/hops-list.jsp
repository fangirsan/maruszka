<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Hops list</title>
</head>

<body>
	<div id="wrapper">
	
		<div id="header">
			<h2>Hops list</h2>		
		</div>
	
	</div>
	
	<div id="container">
	
		<!-- put new button: Add Hop -->
		<input type="button" value="Add Hop"
			   onclick="window.location.href='showAddHopForm'; return false;"
		/>
		
		<!-- add html table here -->
		<table>
			<tr>
				<th>Name</th>
				<th>&alpha; acid min</th>
				<th>&alpha; acid max</th>
				<th>Taste</th>
				<th>Aroma</th>
				<th>Origin</th>
			</tr>
			
			<!-- loop over the hop and print them -->
			<c:forEach var="tempHop" items="${hops}">
			
				<!-- construct update link with malt id -->
				<c:url var="updateLink" value="/hop/showHopUpdateForm">
					<c:param name="hopId" value="${tempHop.id}"/>
				</c:url>
				
				<!--  construct delete link with malt id -->
				<c:url var="deleteLink" value="/hop/delete">
					<c:param name="hopId" value="${tempHop.id}"/>
				</c:url>
				
				<tr>
					<td>${tempHop.hopName}</td>
					<td>${tempHop.alphaAcidMin} %</td>
					<td>${tempHop.alphaAcidMax} %</td>
					<td>${tempHop.hopTaste}</td>
					<td>${tempHop.hopAroma}</td>
					<td>${tempHop.hopOrigin}</td>
					<td>
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
								   onclick="if (!(confirm('Are you sure you want to delete this hop?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<br>
		<a href="${pageContext.request.contextPath}/">Home</a>
		
	</div>
</body>

</html>