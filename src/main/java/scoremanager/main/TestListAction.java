package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		
		
		//検索用データ取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		StudentDao studentDao = new StudentDao();
		
		//表示用の値をセット
		request.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));
		request.setAttribute("class_num_set", classNumDao.filter(teacher.getSchool()));
		request.setAttribute("ent_year_set", studentDao.getEntYears(teacher.getSchool()));
		
		return "test_list.jsp";
		
	}
}
