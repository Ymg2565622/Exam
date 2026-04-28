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
		<form action="ClassCreateExecute.action" method="post">
			<label class="form-label mt-2" for="student-no-input">クラス番号</label>
			<input class="form-control mb-2" id="student-no-input" type="text" name="class_num" value="${classNum}" 
				placeholder="クラス番号を入力してください" required>
			<div class="text-warning">${error}</div>
			<button class="btn btn-secondary mt-2 mb-2" id="end-button" type="submit">登録して終了</button>
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/ClassList.action">戻る</a>
	</c:param>
</c:import>