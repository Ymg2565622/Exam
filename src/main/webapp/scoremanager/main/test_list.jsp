<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
            成績参照
        </h2>

        <form method="get" action="TestListSubjectExecute.action">

            <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                <!-- ===== 科目情報 ===== -->
                <div class="col-2">
                    <label>科目情報</label>
                </div>

                <div class="col-2">
                    <label class="form-label">入学年度</label>
                    <select class="form-select" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}">
                                ${year}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-2">
                    <label class="form-label">クラス</label>
                    <select class="form-select" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}">
                                ${num}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-4">
                    <label class="form-label">科目</label>
                    <select class="form-select" name="f3">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subject_set}">
                            <option value="${subject.cd}">
                                ${subject.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- ✅ 科目検索 -->
                <div class="col-2 text-center">
                    <button type="submit" class="btn btn-secondary">
                        検索
                    </button>
                </div>

                <div class="col-12 mt-2 text-warning">
                    ${errors.get("common")}
                </div>

                <!-- ✅ 区切り線 -->
                <div class="col-12">
                    <div class="my-3 border-top mx-auto" style="width:90%;"></div>
                </div>

                <!-- ===== 学生情報 ===== -->
                <div class="col-2">
                    <label>学生情報</label>
                </div>

                <div class="col-4">
                    <label class="form-label">学生番号</label>
                    <input type="text"
                        name="f5"
                        class="form-control"
                        placeholder="学生番号を入力してください"
                        maxlength="10">
                </div>

                <!-- ✅ 学生検索（ここ重要） -->
                <div class="col-2 text-center">
                    <button type="submit"
                        formaction="TestListStudentExecute.action"
                        class="btn btn-secondary">
                        検索
                    </button>
                </div>

                <div class="col-12 mt-2 text-warning">
                    ${errors.get("f5")}
                </div>

            </div>

        </form>

        <!-- ===== 説明 ===== -->
        <div class="col-12 mt-2">
            <label class="text-info small" style="font-size: 0.8rem;">
                科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
            </label>
        </div>

    </c:param>
</c:import>