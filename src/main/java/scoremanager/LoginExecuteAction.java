package scoremanager;


import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import bean.User;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * ログイン処理を実行するアクション
 * 成功時はユーザーデータをセッションに保存してメニューへ
 * 失敗時は再びログイン画面へ遷移しエラーを表示
 */
public class LoginExecuteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//認証に使用するパラメータの取得
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		List<String> errors = new ArrayList<String>();
		
		//認証用のTeacherDaoを初期化
		TeacherDao teacherDao = new TeacherDao();
		
		//ログイン認証、IDとPWが正しければ教員データを取得、いずれかが誤りの場合null
		Teacher teacher = teacherDao.login(id, password);
		
		//ログイン成功、セッションのユーザーを認証済みにし、教員データも保存してメニューを表示
		if (teacher != null) {
			User user = (User)session.getAttribute("user");
			if (user == null){
				user = new User();
			}
			user.setAuthenticated(true);
			session.setAttribute("user", user);
			session.setAttribute("teacher", teacher);	//ユーザーデータはここでセットしたteacherを使用するように
			return "main/menu.jsp";
		}
		
		//ログイン失敗、メッセージと共に再びログイン画面を表示
		errors.add("IDまたはパスワードが確認できませんでした");
		request.setAttribute("id", id);
		request.setAttribute("errors", errors);
		return "login.jsp";
	}
}