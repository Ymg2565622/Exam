package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		
		//セッションからユーザーデータを取得
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");


		//科目コードが3文字かチェック
		if (cd.length() != 3) {
			//3文字でないのでエラーを出して再表示
			request.setAttribute("error", "科目コードは3文字で入力してください");
		} else {
			//科目コードから科目データを取得
			SubjectDao subjectDao = new SubjectDao();
			Subject subject = subjectDao.get(cd, teacher.getSchool());
			//データがすでに存在すれば、科目コードが重複しているためエラー
			if (subject != null) {
				request.setAttribute("error" ,"科目コードが重複しています");
			} else {
				//データの検証終了、登録処理
				subject = new Subject();
				subject.setCd(cd);
				subject.setName(name);
				subject.setSchool(teacher.getSchool());
				subjectDao.save(subject);	
				return "subject_create_done.jsp";
			}
		}
		
		
		//入力に不備があるため入力画面を再表示
		//以前の値を保持
		request.setAttribute("cd", cd);
		request.setAttribute("name",name);
		return "subject_create.jsp";
		

		
	}
}
