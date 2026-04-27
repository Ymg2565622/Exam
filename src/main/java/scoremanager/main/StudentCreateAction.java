package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 学生情報登録のためのフォームを表示するアクション
 */
public class StudentCreateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
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
		
		
		return "student_create.jsp";
		
	}
}
