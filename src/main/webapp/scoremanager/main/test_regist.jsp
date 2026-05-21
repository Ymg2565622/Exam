<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <!-- ================== 検索部分 ================== -->
        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

        <form method="get">
            <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

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

                <div class="col-2">
                    <label class="form-label">回数</label>
                    <select class="form-select" name="f4">
                        <option value="0">--------</option>
                        <c:forEach var="no" items="${no_set}">
                            <option value="${no}"
                                <c:if test="${no == f4}">selected</c:if>>
                                ${no}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-2 text-center">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>

            </div>
        </form>

        <!-- ================== 検索結果 ================== -->
        <c:if test="${not empty student_list}">

            <div class="mx-3 mb-2">
                科目：${f3_name}（${param.f4}回）
            </div>

            <form method="post" action="TestRegistExecute.action">

                <table class="table table-bordered mx-3">
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>

                    <c:forEach var="student" items="${student_list}">
                        <tr>
                            <td>${student.entYear}</td>
                            <td>${student.classNum}</td>
                            <td>${student.no}</td>
                            <td>${student.name}</td>

                            <td>
                                <!-- ✅ 成績変更対応（ここが重要） -->
                                <input type="number"
                                       name="point_${student.no}"
                                       value="${point_map[student.no]}"
                                       min="0" max="100"
                                       class="form-control"
                                       style="width:90px;">

                                <input type="hidden"
                                       name="regist"
                                       value="${student.no}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <!-- 条件保持 -->
                <input type="hidden" name="f1" value="${param.f1}">
                <input type="hidden" name="f2" value="${param.f2}">
                <input type="hidden" name="f3" value="${param.f3}">
                <input type="hidden" name="f4" value="${param.f4}">

                <div class="mx-3">
                    <button type="submit" class="btn btn-secondary">
                        登録して終了
                    </button>
                </div>

            </form>

        </c:if>

    </c:param>
</c:import>
