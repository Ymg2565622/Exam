package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		//変更する科目コードを取得
		String cd = request.getParameter("cd");
		//変更する科目の取得用Dao
		SubjectDao subjectDao = new SubjectDao();
		//変更する科目を取得
		Subject subject = subjectDao.get(cd, teacher.getSchool());
		
		//初期値をセット
		request.setAttribute("subject_cd", subject.getCd());
		request.setAttribute("subject_name", subject.getName());
		
		return "subject_delete.jsp";
		
	}
}
