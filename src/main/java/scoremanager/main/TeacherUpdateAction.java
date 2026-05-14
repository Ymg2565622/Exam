package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * ユーザー情報更新のためのフォームを表示するアクション
 */
public class TeacherUpdateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {		
		//変更するユーザーのID
		String id = request.getParameter("id");
		
		//変更するユーザーを取得
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = teacherDao.get(id);
		
		//表示用データをセット
		request.setAttribute("id", teacher.getId());
		request.setAttribute("name", teacher.getName());
		return "teacher_update.jsp";
		
	}
}
