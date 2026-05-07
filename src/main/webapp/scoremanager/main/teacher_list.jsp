<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">ユーザー管理</h2>
			<div class="my-2 text-end px-4">
				<a href="TeacherCreate.action">新規登録</a>
			</div>
			<table class="table table-hover">
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th></th>
				</tr>
				<c:forEach var="teacher" items="${teachers}">
					<tr>
						<td>${teacher.id}</td>
						<td>${teacher.name}</td>
						<td><a href="TeacherUpdate.action?id=${teacher.id}">変更</a></td>
					</tr>
				</c:forEach>
			</table>
		</section>
	</c:param>
</c:import>