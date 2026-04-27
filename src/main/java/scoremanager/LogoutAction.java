package scoremanager;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * ログアウトするアクション、ログアウト画面へ遷移
 */
public class LogoutAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションを削除
		session.invalidate();
		return "logout.jsp";
	}
}