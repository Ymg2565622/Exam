package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 学生情報を更新するアクション
 */
public class StudentUpdateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		//更新用データを取得
		int entYear = 0;
		String entYearStr = request.getParameter("ent_year");
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String classNum = request.getParameter("class_num");
		boolean isAttend = false;
		String isAttendStr = request.getParameter("is_attend");
		if (isAttendStr != null) {
			isAttend = true;
		}
		
		//更新用Dao
		StudentDao studentDao = new StudentDao();
		//更新
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setEntYear(entYear);
		student.setAttend(isAttend);
		studentDao.save(student);
		
		return "student_update_done.jsp";
		
	}
}
