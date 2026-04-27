<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
		<form action="StudentCreateExecute.action" method="post">
			<label class="form-label mt-2" for="student-ent-year-select">入学年度</label>
			<select class="form-select mb-2" id="student-ent-year-select" name="ent_year">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ent_year_set}">
					<option value="${year}" <c:if test="${year==ent_year}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
			<div class="text-warning">${errors.get("ent_year")}</div>
			<label class="form-label mt-2" for="student-no-input">学生番号</label>
			<input class="form-control mb-2" id="student-no-input" type="text" name="no" value="${no}" 
				placeholder="学生番号を入力してください" required>
			<div class="text-warning">${errors.get("no")}</div>
			<label class="form-label mt-2" for="student-name-input">氏名</label>
			<input class="form-control mb-2" id="student-name-input" type="text" name="name" value="${name}"
				placeholder="氏名を入力してください" required>
			<label class="form-label mt-2" for="student-class-num-select">クラス</label>
			<select class="form-select mb-2" id="student-class-num-select" name="class_num">
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
			<button class="btn btn-secondary mt-2 mb-2" id="end-button" type="submit">登録して終了</button>
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action">戻る</a>
	</c:param>
</c:import>