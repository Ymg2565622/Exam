<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
		<form action="SubjectUpdateExecute.action" method="post">
			<label class="form-label mt-2" for="subject-cd-input">科目コード</label>
			<input class="form-control mb-2 border-0" id="subject-cd-input"type="text" name="cd" value="${cd}" readonly>
			<div class="text-warning">${error}</div>
			<label class="form-label mt-2" for="subject-name-input">科目名</label>
			<input class="form-control mb-2" id="subject-name-input" type="text" name="name" value="${name}" required>
			<input class="btn btn-primary mb-2" id="login-button" type="submit" value="変更">
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
	</c:param>
</c:import>