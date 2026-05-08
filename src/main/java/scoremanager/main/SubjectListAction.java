package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 科目一覧を表示するアクション。
 * 各ページからの遷移によって実行され、科目一覧を表示する
 */
public class SubjectListAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        // セッションからユーザーデータを取得
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // DAOの生成
        SubjectDao sDao = new SubjectDao();

        // 科目一覧を取得（ログインユーザーの学校で絞り込み）
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // 表示用の値をセット
        request.setAttribute("subjects", subjects);

        // 科目一覧画面へ遷移
        return "subject_list.jsp";
    }
}