<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="bg-primary bg-opacity-10 p-3 d-flex justify-content-between align-items-stretch shadow-sm" style="height:100px;">

  <h1 class="mb-0 ms-3 d-flex align-items-center">得点管理システム</h1>

  <div class="d-flex align-items-end">
    <c:if test="${not empty teacher and not empty teacher.name}">
	  <span class="me-3">${teacher.name}様</span>
	  <a href="${pageContext.request.contextPath}/scoremanager/Logout.action">
      	ログアウト
      </a>
	</c:if>
  </div>

</div>