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
		<p class="mb-5 fw-normal bg-success bg-opacity-50 py-1 px-4 text-center">変更が完了しました</p>
		<a class="d-inline-block mt-5" href="${pageContext.request.contextPath}/scoremanager/main/TeacherList.action">ユーザー一覧</a>
	</c:param>
</c:import>