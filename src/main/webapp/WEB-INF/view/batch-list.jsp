<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Batch list</title>
</head>

<body>
	<div id="wrapper">
	
		<div id="header">
			<h2>Batch list</h2>		
		</div>
	
	</div>
	
	<div id="container">
	
		<!-- put new button: Add Batch -->
		<input type="button" value="Add Batch"
			   onclick="window.location.href='showAddBatchForm'; return false;"
		/>
		
		<!-- add html table here -->
		<table>
			<tr>
				<th>Number</th>
				<th>Style</th>
				<th>Name</th>
				<th>Creation date</th>
				<th>Action</th>
			</tr>
			
			<!-- loop over the batch and print them -->
			<c:forEach var="tempBatch" items="${batches}">
			
				<!-- construct update link with batch id -->
				<c:url var="updateLink" value="/batch/showBatchUpdateForm">
					<c:param name="batchId" value="${tempBatch.id}"/>
				</c:url>
				
				<!--  construct delete link with batch id -->
				<c:url var="deleteLink" value="/batch/delete">
					<c:param name="batchId" value="${tempBatch.id}"/>
				</c:url>
				
				<tr>
					<td>${tempBatch.batchNumber}</td>
					<td>${tempBatch.batchStyle}</td>
					<td>${tempBatch.batchName}</td>
					<td>${tempBatch.batchCreationDate}</td>
					<td>
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
								   onclick="if (!(confirm('Are you sure you want to delete this batch?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<br>
		<a href="${pageContext.request.contextPath}/">Home</a>
		
	</div>
</body>

</html>