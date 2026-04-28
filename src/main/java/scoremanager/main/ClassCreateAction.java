package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * クラス番号登録のためのフォームを表示するアクション
 */
public class ClassCreateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {		
		return "class_create.jsp";
		
	}
}
