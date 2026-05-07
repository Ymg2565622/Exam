<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${user.authenticated}">
<div class="pe-3 border-end" style="width: 300px; min-height: 700px;">
  	<nav>
		 <ul class="list-unstyled">
		   <li class="mb-3">
		   	<a href="${pageContext.request.contextPath}/scoremanager/main/menu.jsp">メニュー</a>
		   </li>
		   <li class="mb-3">
		   	<a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action">学生管理</a>
		   </li>
		   <li class="mb-3">
		   	<label>成績管理</label>
		  		<ul class="list-unstyled ms-3 mt-1">
		  			<li class="mb-3">
		  				<a href="${pageContext.request.contextPath}/scoremanager/main/TestResist.action">成績登録</a>
		  			</li>
		  			<li class="mb-3">
		  				<a href="${pageContext.request.contextPath}/scoremanager/main/TestList.action">成績参照</a>
		  			</li>
		  		</ul>
		  	</li>
		   <li class="mb-3">
		   	<a href="/exam/scoremanager/main/subject_menu.jsp">科目管理</a>
		   </li>
		   <li class="mb-3">
		   	<a href="${pageContext.request.contextPath}/scoremanager/main/ClassList.action">クラス管理</a>
		   </li>
		 </ul>
	</nav>
</div>
</c:if>