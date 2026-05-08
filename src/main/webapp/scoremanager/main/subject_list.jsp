<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
    <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">科目一覧</h2>

    <div class="text-end px-4">
        <a href="/exam/scoremanager/main/subject_create.jsp">科目登録</a>
    </div>
   
		<table class="table table-hover">
			 <colgroup>
			   <col style="width: 13%;"> 
			   <col style="width: 15%;">
			   <col style="width: 20%;">
			   <col style="width: 10%;">
			   <col style="width: 10%;">  
			   <col style="width: 10%;">
			 </colgroup>
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<th></th>
				<th></th>
                <th></th>
                <th></th>
			</tr>
			<c:forEach var="subject" items="${subjects}">
				<tr>
					<td>${subject.cd}</td>
					<td>${subject.name}</td>
					<td></td>
					<td class="text-end">
					<a href="SubjectUpdate.action?cd=${subject.cd}">変更</a>
					</td>
					<td class="text-end">
					<a href="SubjectDelete.action?cd=${subject.cd}">削除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:param>
</c:import>
