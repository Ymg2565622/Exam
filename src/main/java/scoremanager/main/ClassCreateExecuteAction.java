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
 * クラス番号を登録するアクション
 */
public class ClassCreateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		//ユーザーの学校を取得
		School school = teacher.getSchool();
		//登録するクラス番号を取得
		String classNumStr = request.getParameter("class_num");
		//クラス番号テーブル操作用Dao
		ClassNumDao classNumDao = new ClassNumDao();
		//既にクラス番号が登録されているかチェック
		if (classNumDao.get(classNumStr, school) == null) {
			//新規なら登録
			ClassNum classNum = new ClassNum();
			classNum.setClass_num(classNumStr);
			classNum.setSchool(school);
			classNumDao.save(classNum);
		} else {
			//既に登録されていればエラーを出して再表示
			request.setAttribute("error", "クラス番号が重複しています");
			return "class_create.jsp";
		}
		
		return "class_create_done.jsp";
	}
}
