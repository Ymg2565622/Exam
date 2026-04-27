<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<div class="w-75 mx-auto">
		  <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">ログアウト</h2>
		  <p class="mb-5 fw-normal bg-success bg-opacity-50 py-1 px-4 text-center">ログアウトしました</p>	
		  <a href="login.jsp" class="d-inline-block mt-5">ログイン</a>
		</div>
	</c:param>
</c:import>