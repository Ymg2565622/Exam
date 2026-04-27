package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TestResistAction extends Action {
	
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
		String countStr = "";
		Subject subject = null;
		int entYear = 0;
		int count = 0;
		
		Map<String, String[]> paramMap = request.getParameterMap();

		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
		    String name = entry.getKey();
		    String[] values = entry.getValue();

		    System.out.print(name + " = ");
		    for (String v : values) {
		        System.out.print(v + " ");
		    }
		    System.out.println();
		}
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subjectCd = request.getParameter("f3");
		countStr = request.getParameter("f4");
		
		//検索用データ取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		StudentDao studentDao = new StudentDao();
		TestDao testDao = new TestDao();
		
		//入力値チェック、全て入力されているか確認
		if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")
			&& classNum != null && !classNum.isEmpty() && !classNum.equals("0")
			&& subjectCd != null && !subjectCd.isEmpty() && !subjectCd.equals("0")
			&& countStr != null && !countStr.isEmpty() && !countStr.equals("0")) {
			// 全て入力されている
			entYear = Integer.parseInt(entYearStr);
			count = Integer.parseInt(countStr);
			//科目インスタンスを科目コードから取得
			subject = subjectDao.get(subjectCd, school);
			//表示用学生データのリストの作成処理
			List<Student> students = studentDao.filter(school, entYear, classNum, false);
			request.setAttribute("students", students);
			//学生ごとに成績データがあれば得点を初期値として設定
			Map<String, Integer> defaultPoints = new HashMap<>(); 
			for (Student student: students) {
				Test test = testDao.get(student, subject, school, count);
				if (test != null) {
					defaultPoints.put(student.getNo(), test.getPoint());
				}
			}
			request.setAttribute("default_points", defaultPoints);
			
			
		} else {
			// いずれかが入力されていない&検索による表示の場合
			if ("POST".equals(request.getMethod())) {
				request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
			} 
		}
		
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subjectCd);
		request.setAttribute("f4", countStr);
		request.setAttribute("subject_set", subjectDao.filter(school));
		request.setAttribute("class_num_set", classNumDao.filter(school));
		request.setAttribute("ent_year_set", studentDao.getEntYears(school));
		return "test_resist.jsp";
	}
}
