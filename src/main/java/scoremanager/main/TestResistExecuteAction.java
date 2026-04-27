package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestResistExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		School school = teacher.getSchool();
		
		//入力データ保存
		String entYearStr = "";
		String classNum = "";
		String subjectCd = "";
		String countStr = "";
		int entYear = 0;
		int count = 0;
		String[] points = request.getParameterValues("points");
		String[] studentNos = request.getParameterValues("studentNos");
		entYearStr = request.getParameter("ent_year");
		classNum = request.getParameter("class_num");
		subjectCd = request.getParameter("subject");
		countStr = request.getParameter("count");
		if (countStr != null) {
			count = Integer.parseInt(countStr);
		}
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		//データ取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		StudentDao studentDao = new StudentDao();
		//保存用Dao
		TestDao testDao = new TestDao();
		

		//エラー表示用
		String[] errors = new String[points.length];
		//登録用成績インスタンスリスト
		List<Test> tests = new ArrayList<>();
		
		Subject subject = subjectDao.get(subjectCd, school);
		//再表示の必要があるかのフラグ（エラーかどうか）
		boolean isError = false;
		//フォーム入力から成績インスタンスのリストに変換
		for (int i = 0; i < points.length; i++) {
		    if (points[i] == null || points[i].isEmpty()) {
		        // 未入力
		        continue;
		    }
			Integer point = Integer.parseInt(points[i]);
			if (0 <= point && point <= 100) {
				//範囲内
				Test test = new Test();
				test.setClassNum(classNum);
				test.setNo(count);
				test.setPoint(Integer.parseInt(points[i]));
				test.setSchool(school);
				test.setStudent(studentDao.get(studentNos[i]));
				test.setSubject(subject);
				tests.add(test);
			} else {
				//範囲外
				//エラーを追加
				errors[i] = "0～100の範囲で入力してください";
				isError = true;	
			}
		}
	
		
		if (isError) {
			//入力不備があるときは再表示
			//表示用学生データのリストの作成処理
			List<Student> students = studentDao.filter(school, entYear, classNum, false);
			request.setAttribute("students", students);
			request.setAttribute("f1", entYearStr);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subjectCd);
			request.setAttribute("f4", countStr);
			request.setAttribute("errors", errors);
			request.setAttribute("points", points); 
			request.setAttribute("subject_set", subjectDao.filter(school));
			request.setAttribute("class_num_set", classNumDao.filter(school));
			request.setAttribute("ent_year_set", studentDao.getEntYears(school));
			return "test_resist.jsp";
		}
		
		//登録処理
		testDao.save(tests);
		return "test_resist_done.jsp";

	}
	
}
