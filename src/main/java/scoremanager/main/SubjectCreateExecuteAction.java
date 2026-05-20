package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        // セッションからログインユーザー取得
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // 入力値取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // エラーメッセージ用
        Map<String, String> errors = new HashMap<>();

        // DAO
        SubjectDao sDao = new SubjectDao();

        // ===== 入力チェック =====

        // 科目コードは「ちょうど3文字」
        if (cd == null || cd.length() != 3) {
            errors.put("cd", "科目コードは3文字で入力してください");
        }

        // 重複チェック（3文字OK時のみ）
        if (!errors.containsKey("cd")) {
            if (sDao.get(cd, teacher.getSchool()) != null) {
                errors.put("cd", "科目コードが重複しています");
            }
        }
        // 半角英数字チェック
        if (!errors.containsKey("cd")) {
            if (!cd.matches("[A-Za-z0-9]{3}")) {
                errors.put("cd", "科目コードは半角英数字3文字で入力してください");
            }
        }
        // 科目名の重複チェック
        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        } else if (sDao.existsByName(name, teacher.getSchool())) {
            errors.put("name", "同じ科目名が既に登録されています");
        }

        // ===== エラーがある場合 =====
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "subject_create.jsp";
        }

        // ===== 登録処理 =====
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        sDao.save(subject);

        // 一覧再取得
        List<Subject> subjects = sDao.filter(teacher.getSchool());
        request.setAttribute("subjects", subjects);

        return "subject_create_done.jsp";
    }
}