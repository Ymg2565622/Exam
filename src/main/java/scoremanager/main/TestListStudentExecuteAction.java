package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		School school = teacher.getSchool();
		
		String studentNo = "";
		Student student = null;
		
		studentNo = request.getParameter("f4");
		
		
		//データ取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		StudentDao studentDao = new StudentDao();
		TestListStudentDao testListStudentDao = new TestListStudentDao();
		
		//該当学生を取得
		student = studentDao.get(studentNo);
		
		List<TestListStudent> tests = new ArrayList<>();
		if (student != null) {
			tests = testListStudentDao.filter(student);
		}
		
		//表示用の値をセット
		request.setAttribute("student", student);
		request.setAttribute("subject_set", subjectDao.filter(school));
		request.setAttribute("class_num_set", classNumDao.filter(school));
		request.setAttribute("ent_year_set", studentDao.getEntYears(school));
		request.setAttribute("tests", tests);
		
		return "test_student_list.jsp";


		
	}
}
