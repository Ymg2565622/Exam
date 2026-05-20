<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title" value="成績登録完了" />
    <c:param name="scripts" value="" />

    <c:param name="content">

        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
            成績登録完了
        </h2>

        <div class="mx-3">

            <!-- 完了メッセージ -->
            <p class="alert alert-success">
                成績の登録が完了しました。
            </p>

            <!-- リンク -->
            <div class="mt-3">

                <!-- ✅ 修正：.action をつける -->
                <a href="TestRegist.action" class="btn btn-primary me-2">
                    成績管理一覧へ戻る
                </a>

                <!-- ✅ 修正：.action をつける -->
                <a href="TestList.action" class="btn btn-secondary">
                    成績参照へ
                </a>

            </div>

        </div>

    </c:param>

</c:import>
