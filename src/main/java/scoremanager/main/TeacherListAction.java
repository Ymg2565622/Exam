package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * ユーザー一覧を表示する
 */
public class TeacherListAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		//データ取得用Dao
		TeacherDao teacherDao = new TeacherDao();
		
		List<Teacher> list = teacherDao.filter(teacher.getSchool());
		//表示用の値をセット
		request.setAttribute("teachers", list);
		
		return "teacher_list.jsp";
		
	}
}
