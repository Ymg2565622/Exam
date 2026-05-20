<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">

        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
            成績参照
        </h2>

        <!-- ===== フォーム ===== -->
        <form method="get" action="TestListSubjectExecute.action">

            <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                <!-- ===== 科目情報 ===== -->
                <div class="col-2">
                    <label>科目情報</label>
                </div>

                <!-- 入学年度 -->
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

                <!-- クラス -->
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

                <!-- 科目 -->
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

                <!-- 科目検索ボタン -->
                <div class="col-2 text-center">
                    <button type="submit" class="btn btn-secondary">
                        検索
                    </button>
                </div>

                <!-- 入力エラー -->
                <div class="col-12 mt-2 text-warning">
                    ${errors.get("common")}
                </div>

                <!-- ===== 区切り ===== -->
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
                        maxlength="10"
                        placeholder="学生番号を入力してください">
                </div>

                <!-- 学生情報検索ボタン -->
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

        <!-- ===== 検索結果 ===== -->
        <c:choose>

            <c:when test="${not empty test_list_subject}">

                <div class="px-4">
                    検索結果：${test_list_subject.size()}件
                </div>

                <table class="table table-hover">
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th class="text-center">1回</th>
                        <th class="text-center">2回</th>
                    </tr>

                    <c:forEach var="t" items="${test_list_subject}">
                        <tr>
                            <td>${t.entYear}</td>
                            <td>${t.classNum}</td>
                            <td>${t.studentNo}</td>
                            <td>${t.studentName}</td>

                            <td class="text-center">${t.getPoint(1)}</td>
                            <td class="text-center">${t.getPoint(2)}</td>
                        </tr>
                    </c:forEach>

                </table>

            </c:when>

            <c:otherwise>
                <div class="px-4 text-warning">
                    学生情報が存在しませんでした。
                </div>
            </c:otherwise>

        </c:choose>

    </c:param>
</c:import>