package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * ユーザー登録のためのフォームを表示するアクション
 */
public class TeacherCreateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {		
		return "teacher_create.jsp";
		
	}
}
