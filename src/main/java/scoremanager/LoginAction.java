package scoremanager;


import bean.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		if (user == null){
			//セッションにユーザーが無ければ未認証ユーザーを新たに保持
			user = new User();
			user.setAuthenticated(false);
			session.setAttribute("user", user);
		}
		
		//シーケンス図に基づいてユーザーが認証済みである場合もログイン画面へ
//		if (user.isAuthenticated()) {
//			//認証（ログイン）済みならメニュー画面へ
//			return "main/menu.jsp";
//		}
				
		return "login.jsp";
		
		
	}
}
