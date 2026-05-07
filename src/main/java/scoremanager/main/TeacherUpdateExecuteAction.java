package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * ユーザー情報を更新するアクション
 */
public class TeacherUpdateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//入力されたデータを取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		//エラー出力用
		Map<String, String> errors = new HashMap<>();
		
		//パスワードを変更するかどうか新しいパスワードが入力されているかで判断
		boolean changePassword = newPassword != null && !newPassword.isBlank();
		
		//データ操作用Dao
		TeacherDao teacherDao = new TeacherDao();
		//操作するユーザーデータ
		Teacher teacher = teacherDao.get(id);
		
		if (!changePassword) {
			//名前のみ。パスワード確認不要
			teacher.setName(name);
			teacherDao.save(teacher);
			
			return "teacher_update_done.jsp";
		} else {
			//パスワード変更含む。パスワード確認必要
			//現在のパスワードの未入力チェック
			if (currentPassword == null || currentPassword.isBlank()) {
				errors.put("password", "パスワードを変更する場合は現在のパスワードを入力してください");
			} else if (!teacher.getPassword().equals(currentPassword)) {
				//現在のパスワードの一致チェック
				errors.put("password", "パスワードが異なります");
			}
			//確認用パスワード一致チェック
			if (!newPassword.equals(confirmPassword)) {
			    errors.put("confirmPassword", "新しいパスワードと確認用パスワードが一致しません");
			}
			
			//エラーがないなら更新処理を実行
			if (errors.isEmpty()) {
				teacher.setName(name);
				teacher.setPassword(newPassword);
				teacherDao.save(teacher);
				return "teacher_update_done.jsp";
			}
			
		}
		
		//エラー時の再表示
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("errors", errors);
		return "teacher_update.jsp";
	}
}
