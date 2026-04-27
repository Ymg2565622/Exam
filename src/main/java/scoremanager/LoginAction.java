package scoremanager;


import bean.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * ログイン画面を表示するアクション
 */
public class LoginAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		/**
		 * Userインスタンスについては、クラス図に記述はあるが、シーケンス図で使用しないため、
		 * 他機能の実装で使用しないように
		 */
		User user = (User)session.getAttribute("user");
		if (user == null){
			//セッションにユーザーが無ければ未認証ユーザーを新たに保持
			user = new User();
			user.setAuthenticated(false);
			session.setAttribute("user", user);
		}
				
		return "login.jsp";
		
		
	}
}
