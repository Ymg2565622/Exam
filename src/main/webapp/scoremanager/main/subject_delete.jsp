<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
		<form action="SubjectDeleteExecute.action" method="post">
			<p>「${subject_name}(${subject_cd})」を削除してもよろしいですか</p>
			<input type="hidden" name="subject_cd" value="${subject_cd}" required>
			<input type="hidden" name="subject_name" value="${subject_name}" required>
			<input class="btn btn-danger mb-2" id="login-button" type="submit" value="削除">
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
	</c:param>
</c:import>