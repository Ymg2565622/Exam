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
			<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="border mx-3 mb-3 py-2 rounded">
			
			    <form action="TestResist.action" method="post">
			        <div class="row align-items-center px-2 mb-2">
			
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
			            
			            <div class="col-2">
			                <label class="form-label">回数</label>
			                <select class="form-select" name="f4">
			                    <option value="0">--------</option>
			                    <c:forEach var="count" begin="1" end="5">
			                        <option value="${count}" <c:if test="${count==f4}">selected</c:if>>
			                            ${count}
			                        </option>
			                    </c:forEach>
			                </select>
			            </div>
			
			            <div class="col-2 text-center">
			                <button class="btn btn-secondary">検索</button>
			            </div>
			        </div>
			    </form>
				<div class="mx-1 text-warning">${error}</div>
			</div>
			
			<c:if test="${students.size()>0}">
				<div>科目：${f3}（${f4}回）</div>
				<form action="TestResistExecute.action" method="post">
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
						</tr>
						<c:forEach var="student" items="${students}" varStatus="status">
							<tr>
								<td>${f1}</td>
								<td>${f2}</td>
								<td>${student.no}</td>
								<td>${student.name}</td>
								<td>
									<input type="text" name="points" 
										value="${points != null ? points[status.index] : default_points[student.no]}" 
										class="form-control-sm border border-secondary">
									<div class="text-warning">${errors[status.index]}</div>
								</td>
								
								<td style="display:none;">
								    <input type="hidden" name="studentNos" value="${student.no}">
								</td>
							</tr>
						</c:forEach>
					</table>
					
					<input type="hidden" name="count" value="${f4}">
	  				<input type="hidden" name="subject" value="${f3}">
	  				<input type="hidden" name="class_num" value="${f2}">
	  				<input type="hidden" name="ent_year" value="${f1}">
	  				
					
					<button class="btn btn-secondary">登録して終了</button>				
				</form>
			</c:if>
		</section>
	</c:param>
</c:import>