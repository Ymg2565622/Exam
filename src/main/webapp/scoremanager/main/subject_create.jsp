<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
            科目情報登録
        </h2>

        <form action="/exam/scoremanager/main/SubjectCreateExecute.action"
              method="post"
              class="px-4">

            <!-- 科目コード -->
            <label for="subject-cd-input" class="form-label mt-2">
                科目コード
            </label>
            <input
                id="subject-cd-input"
                type="text"
                name="cd"
                class="form-control"
                value="${cd}"
                maxlength="3"
                required
                pattern="[A-Za-z0-9]+"
    			title="半角英数字のみ入力できます"
            >

            <!--  科目コードの警告メッセージ -->
            <div class="text-warning">
                ${errors.cd}
            </div>

            <!-- 科目名 -->
            <label for="subject-name-input" class="form-label mt-3">
                科目名
            </label>
            <input
                id="subject-name-input"
                type="text"
                name="name"
                class="form-control"
                value="${name}"
                maxlength="20"
                required
            >
			
			<!--  科目コードの警告メッセージ -->
            <div class="text-warning">
                ${errors.name}
            </div>
            
            <!-- 登録ボタン -->
            <div class="mt-4">
                <button type="submit" class="btn btn-primary">
                    登録
                </button>
            </div>

        </form>
    </c:param>
</c:import>