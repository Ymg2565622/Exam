package scoremanager;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
//		//セッションからユーザーデータを削除
//		session.removeAttribute("user");
//		//シーケンス図に書かれていないが教員データも残っているので一応消す
//		session.removeAttribute("teacher");
		
		//セッションを削除
		session.invalidate();
		return "logout.jsp";
	}
}