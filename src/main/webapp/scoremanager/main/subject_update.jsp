<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

<h2>科目情報変更</h2>

<!--  エラーメッセージ表示 -->
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="SubjectUpdateExecute.action" method="post">

    <!-- 科目コード（変更不可） -->
    <p>
        <label>科目コード：</label>
        <input type="text" name="cd" value="${cd}" readonly>
    </p>

    <!-- 科目名（変更可能） -->
    <p>
        <label>科目名：</label>
        <input type="text" name="name" value="${name}">
    </p>

    <p>
        <input type="submit" value="更新">
        <input type="button" value="戻る" onclick="history.back()">
    </p>

</form>

    </c:param>
</c:import>
