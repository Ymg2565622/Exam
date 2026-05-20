package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

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

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        School school = teacher.getSchool();

        SubjectDao dao = new SubjectDao();

        Map<String, String> errors = new HashMap<>();

        // ★ 科目が存在するかチェック（他から削除された場合）
        Subject current = dao.get(cd, school);
        if (current == null) {
            errors.put("notfound", "科目が存在していません。");
            request.setAttribute("errors", errors);
            return "subject_update.jsp";
        }

        //以下二つのチェックは仕様書では定義されていないので注意
//        // 科目名未入力
//        if (name == null || name.trim().isEmpty()) {
//            errors.put("name", "科目名を入力してください。");
//        }
//
//        // 同名科目チェック（自分以外）
//        List<Subject> subjects = dao.filter(school);
//        for (Subject s : subjects) {
//            if (!s.getCd().equals(cd) && s.getName().equals(name)) {
//                errors.put("name", "同じ名前の科目が既に登録されています。");
//                break;
//            }
//        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "subject_update.jsp";
        }

        // 更新処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        dao.save(subject);

        return "subject_update_done.jsp";
    }
}

