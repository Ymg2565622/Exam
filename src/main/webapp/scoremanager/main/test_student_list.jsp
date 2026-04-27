<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<div class="border mx-3 mb-3 py-2 rounded">
			
			    <form action="TestListSubjectExecute.action" method="post">
			        <div class="row align-items-center px-2 mb-2">
			            <div class="col-2">
			                <p class="mx-3 mb-0">科目情報</p>
			            </div>
			
			            <div class="col-2">
			                <label class="form-label">入学年度</label>
			                <select class="form-select" name="f1">
			                    <option value="0">--------</option>
			                    <c:forEach var="year" items="${ent_year_set}">
			                        <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
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
			                        <option value="${num}" <c:if test="${num==f2}">selected</c:if>>
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
			                        <option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>
			                            ${subject.name}
			                        </option>
			                    </c:forEach>
			                </select>
			            </div>
			
			            <div class="col-2 text-center">
			                <button class="btn btn-secondary">検索</button>
			            </div>
			        </div>
			    </form>
			
			    <hr class="mx-4 mt-0 mb-2 opacity-10">
			
			    <form action="TestListStudentExecute.action" method="post">
			        <div class="row align-items-center px-2">
			            <div class="col-2">
			                <p class="mx-3 mb-0">学生情報</p>
			            </div>
			
			            <div class="col-4">
			                <label class="form-label" id="student-no-input">学生番号</label>
							<input class="form-control" id="student-no-input"type="text" name="f4" value="${f4}" required>
			            </div>
			            			
			            <div class="col-2 text-center">
			                <button class="btn btn-secondary">検索</button>
			            </div>
			        </div>
			    </form>
			
			</div>
			
			<div>氏名：${student.name}(${student.no})</div>
			<c:choose>
				<c:when test="${tests.size()>0}">
					<table class="table table-hover">
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>
						</tr>
						<c:forEach var="test" items="${tests}">
							<tr>
								<td>${test.subjectName}</td>
								<td>${test.subjectCd}</td>
								<td>${test.num}</td>
								<td>${test.point}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>成績情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>