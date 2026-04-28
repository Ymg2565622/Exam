package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * クラス一覧を表示する
 */
public class ClassListAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		//クラス一覧取得用Dao
		ClassNumDao classNumDao = new ClassNumDao();
		//ユーザーの学校を基にクラス一覧を取得
		List<String> classNums = classNumDao.filter(teacher.getSchool());
		//表示用の値をセット
		request.setAttribute("classNums", classNums);
		
		return "class_list.jsp";
		
	}
}
