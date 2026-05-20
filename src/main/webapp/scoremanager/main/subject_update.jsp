<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

        <form action="SubjectUpdateExecute.action" method="post">

            <!-- 科目コード -->
            <div class="mb-3">
                <label class="form-label">科目コード</label>
                <input type="text" class="form-control-plaintext" name="cd" value="${cd}" readonly>

                <c:if test="${not empty errors.cd}">
                    <div class="text-warning">${errors.cd}</div>
                </c:if>
            </div>

            <!-- 科目名 -->
            <div class="mb-3">
                <label class="form-label">科目名</label>
                <input type="text" class="form-control" name="name" value="${name}"
                       placeholder="科目名を入力してください" required>

                <c:if test="${not empty errors.name}">
                    <div class="text-warning">${errors.name}</div>
                </c:if>
            </div>

            <input class="btn btn-primary mb-3" type="submit" value="変更">

        </form>

        <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>

    </c:param>
</c:import>
