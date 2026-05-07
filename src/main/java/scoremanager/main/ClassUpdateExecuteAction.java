package scoremanager.main;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * クラス番号を更新するアクション
 */
public class ClassUpdateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		//ユーザーの学校を取得
		School school = teacher.getSchool();
		//更新するクラス番号を取得
		String classNumStr = request.getParameter("class_num");
		//新しいクラス番号を取得
		String newClassNumStr = request.getParameter("new_class_num");
		
		//同じ番号ならエラーを出す
		if (classNumStr.equals(newClassNumStr)) {
			request.setAttribute("error", "変更前と同じ番号です");
			return "class_update.jsp";
		}
		
		//クラス番号テーブル操作用Dao
		ClassNumDao classNumDao = new ClassNumDao();

		
		//既に同じクラス番号が登録されているかチェック
		if (classNumDao.get(newClassNumStr, school) == null) {
			//クラス番号の更新処理
			//元のクラス番号を取得
			ClassNum classNum = classNumDao.get(classNumStr, school);
			//更新処理
			classNumDao.save(classNum, newClassNumStr);
		} else {
			//既に登録されていればエラーを出して再表示
			request.setAttribute("error", "クラス番号が重複しています");
			return "class_update.jsp";
		}
		
		return "class_update_done.jsp";
	}
}
