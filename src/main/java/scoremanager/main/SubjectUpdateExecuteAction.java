package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		//保存するデータを取得
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		
		//保存する科目インスタンスを設定
		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());
		
		
		SubjectDao subjectDao = new SubjectDao();
		if (subjectDao.get(cd, teacher.getSchool()) != null) {
			subjectDao.save(subject);
			return "subject_update_done.jsp";
		} else {
			//変更中に該当データが削除のため、再表示
			request.setAttribute("cd" , cd);
			request.setAttribute("name", name);
			request.setAttribute("error", "科目が存在していません");
			return "subject_update.jsp";
		}
	}
}
