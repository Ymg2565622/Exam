package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * ユーザーを登録するアクション
 */
public class TeacherCreateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		//ユーザーの学校を取得
		School school = teacher.getSchool();
		//データを取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		//エラー出力用
		Map<String, String> errors = new HashMap<>();
		
		// パスワード一致チェック
		if (!password.equals(confirmPassword)) {
		    errors.put("password", "パスワードが一致しません");
		}
		// ID重複チェック
		TeacherDao teacherDao = new TeacherDao();
		if (teacherDao.get(id) != null) {
		    errors.put("id", "ユーザーIDが重複しています");
		}

		// エラーなしなら登録
		if (errors.isEmpty()) {
		    Teacher newTeacher = new Teacher();
		    newTeacher.setId(id);
		    newTeacher.setName(name);
		    newTeacher.setPassword(password);
		    newTeacher.setSchool(school);
		    teacherDao.save(newTeacher);
		    return "teacher_create_done.jsp";
		}
		
		//エラー時の再表示処理
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("errors", errors);
		return "teacher_create.jsp";
	}
}
