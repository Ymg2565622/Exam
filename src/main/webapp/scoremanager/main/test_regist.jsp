<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

        <form method="get">
            <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                <!-- 入学年度 -->
                <div class="col-2">
                    <label class="form-label" for="f1-select">入学年度</label>
                    <select class="form-select" id="f1-select" name="f1">
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
                    <label class="form-label" for="f2-select">クラス</label>
                    <select class="form-select" id="f2-select" name="f2">
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
                    <label class="form-label" for="f3-select">科目</label>
                    <select class="form-select" id="f3-select" name="f3">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subject_set}">
                            <option value="${subject.cd}"
                                <c:if test="${subject.cd == f3}">selected</c:if>>
                                ${subject.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- 回数 -->
                <div class="col-2">
                    <label class="form-label" for="f4-select">回数</label>
                    <select class="form-select" id="f4-select" name="f4">
                        <option value="0">--------</option>
                        <c:forEach var="no" items="${no_set}">
                            <option value="${no}"
                                <c:if test="${no == f4}">selected</c:if>>
                                ${no}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <!-- 検索ボタンを配置 -->
                <div class="col-2 text-center">
                	<button class="btn btn-secondary" id="filter-button">絞込</button>
                </div>
                <div class="mt-2 text-warning">${errors.get("f1")}</div>
            </div>
        </form>
    </c:param>
</c:import>