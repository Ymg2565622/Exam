package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // 更新対象の科目コードを取得
        String cd = request.getParameter("cd");

        // 学校情報をセッションから取得（ログイン時に保存されている想定）
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        School school = teacher.getSchool();

        // Dao の準備
        SubjectDao subjectDao = new SubjectDao();

        // 科目を取得
        
        Subject subject = subjectDao.get(cd, school);

        // 表示用データをセット
        request.setAttribute("cd", subject.getCd());
        request.setAttribute("name", subject.getName());
        request.setAttribute("school", subject.getSchool());

        // 更新フォーム JSP へ
        return "subject_update.jsp";
    }
}
