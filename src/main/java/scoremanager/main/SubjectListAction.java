package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		//科目データ取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		//科目データをユーザーの所属学校を基に取得
		List<Subject> subjects = subjectDao.filter(teacher.getSchool());
		//表示用科目データリストをセット
		request.setAttribute("subjects", subjects);
		
		return "subject_list.jsp";
		
	}
}
