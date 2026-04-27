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
		<form action="StudentUpdateExecute.action" method="post">
			<label class="form-label" for="student-ent-year-input">入学年度</label>
			<input class="form-control mb-3 border-0" id="student-ent-year-input"type="text" name="ent_year" value="${ent_year}" readonly>
			<label class="form-label" for="student-no-input">学生番号</label>
			<input class="form-control mb-3 border-0" id="student-no-input"type="text" name="no" value="${no}" readonly>
			<label class="form-label" for="student-name-input">氏名</label>
			<input class="form-control mb-3" id="student-name-input" type="text" name="name" value="${name}" required>
			<label class="form-label" for="student-class-num-select">クラス</label>
			<select class="form-select mb-3" id="student-class-num-select" name="class_num">
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
			<label class="form-label" for="student-is-attend-input">在学中</label>
			<input class="form-check mb-3" id="student-is-attend-input" type="checkbox" name="is_attend"
			<c:if test="${is_attend}">checked</c:if>>
			<input class="btn btn-primary mb-3" id="login-button" type="submit" value="変更">
		</form>
		<a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action">戻る</a>
	</c:param>
</c:import>