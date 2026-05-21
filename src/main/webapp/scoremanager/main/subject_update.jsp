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

        <!-- ★ novalidate を削除 -->
        <form action="SubjectUpdateExecute.action" method="post">

            <!-- 科目コード -->
            <div class="mb-3">
                <label class="form-label">科目コード</label>
                <input type="text" class="form-control-plaintext" name="cd" value="${cd}" readonly>

                <c:if test="${not empty errors.notfound}">
                    <div class="text-warning fw-bold small">${errors.notfound}</div>
                </c:if>

                <c:if test="${not empty errors.cd}">
                    <div class="text-warning small">${errors.cd}</div>
                </c:if>
            </div>

            <!-- 科目名 -->
            <div class="mb-3">
                <label class="form-label">科目名</label>
                <input type="text"
                       class="form-control"
                       name="name"
                       value="${name}"
                       placeholder="科目名を入力してください"
                       required> <!-- ★ required が吹き出しを出す -->

                <!-- 重複エラー（通常の警告表示） -->
                <c:if test="${errors.name == '同じ名前の科目が既に登録されています。'}">
                    <div class="text-warning small mt-1">
                        ${errors.name}
                    </div>
                </c:if>
            </div>

            <input type="submit" class="btn btn-primary mb-3" value="変更">

        </form>

        <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action" class="btn btn-link">戻る</a>

    </c:param>
</c:import>

<style>
/* Bootstrap標準フォームに合わせた軽微な調整 */
.form-control:invalid {
    border-color: #dc3545;
}
.form-control:focus:invalid {
    box-shadow: 0 0 0 0.2rem rgba(220, 53, 69, 0.25);
}
</style>





