<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">ユーザー情報変更</h2>
		<form action="TeacherUpdateExecute.action" method="post">
			<label class="form-label" for="teacher-id-input">ユーザーID</label>
			<input class="form-control mb-3 border-0" id="teacher-id-input"type="text" name="id" value="${id}" readonly>
			<label class="form-label mt-2" for="teacher-name-input">名前(表示用)</label>
			<input class="form-control mb-2" id="teacher-name-input" type="text" name="name" value="${name}" 
				placeholder="名前を入力してください">
			<div class="form-text">
			    パスワードを変更する場合のみ以下の欄を入力してください
			</div>
			<label class="form-label mt-2" for="teacher-currentPassword-input">現在のパスワード</label>
			<input class="form-control mb-2" id="teacher-currentPassword-input" type="password" name="currentPassword" 
				placeholder="現在のパスワードを入力してください">
			<div class="text-warning">${errors.get("password")}</div>
			<label class="form-label mt-2" for="teacher-newPassword-input">新しいパスワード</label>
			<input class="form-control mb-2" id="teacher-newPassword-input" type="password" name="newPassword" 
				placeholder="新しいパスワードを入力してください">
			<div class="text-warning">${errors.get("confirmPassword")}</div>
			<label class="form-label mt-2" for="teacher-confirmPassword-input">新しいパスワード確認用</label>
			<input class="form-control mb-2" id="teacher-confirmPassword-input" type="password" name="confirmPassword" 
				placeholder="同じパスワードを入力してください">
			<input class="btn btn-primary mb-3" id="login-button" type="submit" value="変更">
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/TeacherList.action">戻る</a>
	</c:param>
</c:import>