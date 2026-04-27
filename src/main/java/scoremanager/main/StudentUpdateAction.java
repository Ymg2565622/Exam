package scoremanager.main;

import java.util.List;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		//変更する学生番号を取得
		String no = request.getParameter("no");
		//変更する学生の取得用Dao
		StudentDao studentDao = new StudentDao();
		//変更する学生を取得
		Student student = studentDao.get(no);
		
		//クラス一覧の取得用Dao
		ClassNumDao classNumDao = new ClassNumDao();
		//学生の学校コードからクラス番号の一覧を取得
		List<String> list = classNumDao.filter(student.getSchool());
		

		
		//表示用の値をセット
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year", student.getEntYear());
		request.setAttribute("no", student.getNo());
		request.setAttribute("name", student.getName());
		request.setAttribute("class_num", student.getClassNum());
		request.setAttribute("is_attend", student.isAttend());
		
		return "student_update.jsp";
		
	}
}
