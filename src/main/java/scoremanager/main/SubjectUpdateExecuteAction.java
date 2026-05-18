package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 学校情報を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        School school = teacher.getSchool();

        SubjectDao dao = new SubjectDao();

        // 現在の科目情報を取得
        Subject current = dao.get(cd, school);

        //  同名科目が存在するかチェック（ただし自分自身は除外）
        if (!current.getName().equals(name) && dao.existsByName(name, school)) {

            request.setAttribute("error", "同じ名前の科目が既に登録されています。");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);

            return "subject_update.jsp";  // 入力画面に戻す
        }

        // 更新する Subject を作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // 保存（更新）
        dao.save(subject);

        // 完了画面へ
        return "subject_update_done.jsp";
    }
}

