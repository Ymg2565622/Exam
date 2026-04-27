package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		int entYear = 0;
		String entYearStr = request.getParameter("ent_year");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String classNum = request.getParameter("class_num");
		Map<String, String> errors = new HashMap<>();
		
		
		//入学年度の入力をチェック
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (entYear != 0) {
			//入学年度が有効
			//学生番号の重複チェック
			StudentDao studentDao = new StudentDao();
			Student student = studentDao.get(no);
			if (student != null) {
				//既に同じ学生番号の生徒が存在する（重複している）ため、登録を拒否
				errors.put("no", "学生番号が重複しています");
				request.setAttribute("errors", errors);
			} else {
				//入力値チェック完了、登録処理を開始
				student = new Student();
				student.setNo(no);
				student.setName(name);
				student.setClassNum(classNum);
				student.setEntYear(entYear);
				student.setAttend(true);
				student.setSchool(teacher.getSchool());
				studentDao.save(student);
				return "student_create_done.jsp";
			}
		} else {
			//エラーメッセージ
			errors.put("ent_year", "入学年度を選択してください");
			request.setAttribute("errors", errors);
		}
		//再表示用
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		ClassNumDao cNumDao = new ClassNumDao();
		//プルダウン用の10年前～10年後のリスト
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}
		//ログインユーザーの学校コードからクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		//表示用の値をセット
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("ent_year", entYear);
		request.setAttribute("no", no);
		request.setAttribute("name", name);
		request.setAttribute("class_num", classNum);
		return "student_create.jsp";
		

		
	}
}
