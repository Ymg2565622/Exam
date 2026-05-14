package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * クラス番号更新のためのフォームを表示するアクション
 */
public class ClassUpdateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {		
		request.setAttribute("classNum", request.getParameter("class_num"));
		return "class_update.jsp";
		
	}
}
