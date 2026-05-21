<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">

        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
            成績一覧（学生）
        </h2>

        <form method="get" action="TestListStudentExecute.action">

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
                            <option value="${year}"
                                <c:if test="${year == f1}">selected</c:if>>
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
                            <option value="${num}"
                                <c:if test="${num == f2}">selected</c:if>>
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
                            <option value="${subject.cd}"
                                <c:if test="${subject.cd == f3}">selected</c:if>>
                                ${subject.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- ✅ 科目検索 -->
                <div class="col-2 text-center">
                    <button type="submit"
                        formaction="TestListSubjectExecute.action"
                        class="btn btn-secondary">
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
                        value="${f5}"
                        placeholder="学生番号を入力してください"
                        maxlength="10"
                        required>
                </div>

                <!-- ✅ 学生検索 -->
                <div class="col-2 text-center">
                    <button type="submit" class="btn btn-secondary">
                        検索
                    </button>
                </div>

                <div class="col-12 mt-2 text-warning">
                    ${errors.get("f5")}
                </div>

            </div>

        </form>

        <!-- ===== 学生情報表示 ===== -->
        <c:if test="${not empty student}">
            <div class="px-4 mb-2">
                氏名：${student.name}（${student.no}）
            </div>
        </c:if>

        <!-- ===== 検索結果 ===== -->
        <c:choose>

            <c:when test="${not empty test_list_student}">

                <table class="table table-hover">
                    <colgroup>
                        <col style="width: 40%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                    </colgroup>

                    <tr>
                        <th class="text-start">科目名</th>
                        <th class="text-center">科目コード</th>
                        <th class="text-center">回数</th>
                        <th class="text-center">点数</th>
                    </tr>

                    <c:forEach var="t" items="${test_list_student}">
                        <tr>
                            <td class="text-start">${t.subjectName}</td>
                            <td class="text-center">${t.subjectCd}</td>
                            <td class="text-center">${t.num}</td>
                            <td class="text-center">${t.point}</td>
                        </tr>
                    </c:forEach>

                </table>

            </c:when>

            <c:otherwise>
                <c:if test="${not empty student}">
                    <div class="px-4 text-warning">
                        成績データが存在しません。
                    </div>
                </c:if>
            </c:otherwise>

        </c:choose>

    </c:param>
</c:import>