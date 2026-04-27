<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts">
		<script>
		function togglePassword() {
		    const pass = document.getElementById("password");
		
		    if (pass.type === "password") {
		        pass.type = "text";
		    } else {
		        pass.type = "password";
		    }
		}
		</script>
	</c:param>
	
	<c:param name="content">
		<div class="d-flex justify-content-center align-items-start">
		  <div class="bg-white shadow-sm" style="width: 600px;">
		
		    <h2 class="text-center bg-secondary bg-opacity-10 py-2 mb-0">
		      ログイン
		    </h2>

		    <div class="p-4">
				<c:if test="${errors.size()>0}">
					<div>
						<ul class="list-unstyled">
							<c:forEach var="error" items="${errors}">
								<li>${error}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
		      <form action="LoginExecute.action" method="post">

				<div class="mb-3">
				  <label for="id" class="form-label text-muted">Ⅰ　Ｄ</label>
				  <input type="text" class="form-control" id="id" name="id"
				    placeholder="半角でご入力下さい" style="ime-mode: disabled" value="${id}" required>
				</div>
				
				<div class="mb-3">
				  <label for="password" class="form-label text-muted">パスワード</label>
				  <input type="password" class="form-control" id="password" name="password"
				    placeholder="30文字以内の半角英数字でご入力下さい"
				    style="ime-mode: disabled" autocomplete="off" required>
				</div>
		
				<div class="form-check mb-3 d-flex justify-content-center">
				  <input class="form-check-input" type="checkbox" onclick="togglePassword()" id="showPass">
				  <label class="form-check-label ms-2" for="showPass">
				    パスワードを表示
				  </label>
				</div>
				
				<div class="text-center">
				  <button type="submit" class="btn btn-primary px-4">
				    ログイン
				  </button>
				</div>
		
		      </form>
		
		    </div>
		  </div>
		</div>
	</c:param>
</c:import>