<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">クラス情報登録</h2>
		<form action="ClassUpdateExecute.action" method="post">
			<label class="form-label" for="class-no-input">クラス番号</label>
			<input class="form-control mb-3 " id="class-no-input"type="text" name="new_class_num" value="${classNum}">
			<div class="text-warning">${error}</div>
			<input class="btn btn-primary mb-3" id="login-button" type="submit" value="変更">
			<input type="hidden" name="class_num" value="${classNum}">
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/ClassList.action">戻る</a>
	</c:param>
</c:import>