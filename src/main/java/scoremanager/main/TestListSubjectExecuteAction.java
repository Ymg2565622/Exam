package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		School school = teacher.getSchool();
		
		String entYearStr = "";
		String classNum = "";
		String subjectCd = "";
		Subject subject = null;
		int entYear = 0;
		
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subjectCd = request.getParameter("f3");
		
		//データ取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		StudentDao studentDao = new StudentDao();
		TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
		
		
		//入力値チェック、全て入力されているか確認
		if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")
			&& classNum != null && !classNum.isEmpty() && !classNum.equals("0")
			&& subjectCd != null && !subjectCd.isEmpty() && !subjectCd.equals("0")) {
			// 全て入力されている
			entYear = Integer.parseInt(entYearStr);
			//科目インスタンスを科目コードから取得
			subject = subjectDao.get(subjectCd, school);
			//成績データを取得
			List<TestListSubject> tests = testListSubjectDao.filter(entYear, classNum, subject, school);
			
			//表示用の値をセット
			request.setAttribute("f1", entYear);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subjectCd);
			request.setAttribute("selected_subject_name", subject.getName());
			request.setAttribute("subject_set", subjectDao.filter(school));
			request.setAttribute("class_num_set", classNumDao.filter(school));
			request.setAttribute("ent_year_set", studentDao.getEntYears(school));
			request.setAttribute("tests", tests);
			
			return "test_subject_list.jsp";
		} else {
			// いずれかが入力されていない
			request.setAttribute("error", "入学年度とクラスと科目を選択してください");
			request.setAttribute("f1", entYear);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subjectCd);
			request.setAttribute("subject_set", subjectDao.filter(school));
			request.setAttribute("class_num_set", classNumDao.filter(school));
			request.setAttribute("ent_year_set", studentDao.getEntYears(school));
			return "test_list.jsp";
		}

		
	}
}
