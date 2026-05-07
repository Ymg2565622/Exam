<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">ユーザー情報登録</h2>
		<form action="TeacherCreateExecute.action" method="post">
			<label class="form-label mt-2" for="teacher-id-input">ユーザーID</label>
			<input class="form-control mb-2" id="teacher-id-input" type="text" name="id" value="${id}" 
				placeholder="ユーザーIDを入力してください" required>
			<div class="text-warning">${errors.get("id")}</div>
			<label class="form-label mt-2" for="teacher-name-input">名前</label>
			<input class="form-control mb-2" id="teacher-name-input" type="text" name="name" value="${name}" 
				placeholder="名前を入力してください" required>
			<label class="form-label mt-2" for="teacher-password-input">パスワード</label>
			<input class="form-control mb-2" id="teacher-password-input" type="password" name="password" 
				placeholder="パスワードを入力してください" required>
			<div class="text-warning">${errors.get("password")}</div>
			<label class="form-label mt-2" for="teacher-confirmPassword-input">パスワード確認用</label>
			<input class="form-control mb-2" id="steacher-confirmPassword-input" type="password" name="confirmPassword" 
				placeholder="同じパスワードを入力してください" required>
			<button class="btn btn-secondary mt-2 mb-2" id="end-button" type="submit">登録して終了</button>
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/TeacherList.action">戻る</a>
	</c:param>
</c:import>