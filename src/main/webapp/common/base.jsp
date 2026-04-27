<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>
 <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
		  <jsp:include page="/common/header.jsp" />
		</div>
	    <div class="d-flex shadow-sm p-4">
	        <jsp:include page="/common/sidebar.jsp" />
	
	      <div class="flex-grow-1 ms-3">
	      	${param.scripts}
	        ${param.content}
	      </div>
	    </div>
		<jsp:include page="/common/footer.jsp" />
	</div>
</body>
</html>