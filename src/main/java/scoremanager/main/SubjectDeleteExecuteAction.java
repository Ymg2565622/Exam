package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		//削除するデータを取得
		String cd = request.getParameter("subject_cd");
		String name = request.getParameter("subject_name");
		
		//削除する科目インスタンスを設定
		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());
		
		//削除用Dao
		SubjectDao subjectDao = new SubjectDao();
		subjectDao.delete(subject);
		
		return "subject_delete_done.jsp";
	}
}
