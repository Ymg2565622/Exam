<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">科目メニューページ</h2>
		<div class="row text-center px-4 fs-3 my-5">
			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #bdd;">
				<a href="/exam/scoremanager/main/subject_list.jsp">科目一覧</a>
			</div>	
			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #bdb;">
				<a href="/scoremanager/main/StudentList.action">科目登録</a>
			</div>
		</div>
		<div class="row text-center px-4 fs-3 my-5">
			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #bbd;">
			<a href="/exam/scoremanager/main/subject_menu.jsp">科目変更</a>
			</div>
			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; background-color: #dbb;">
			<a href="/exam/scoremanager/main/subject_menu.jsp">科目削除</a>
			</div>
		</div>
		
	</c:param>
</c:import>