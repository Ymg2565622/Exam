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

public class SubjectUpdateAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        String cd = request.getParameter("cd");

        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        School school = teacher.getSchool();

        SubjectDao subjectDao = new SubjectDao();

        Subject subject = subjectDao.get(cd, school);

        // ★ 削除されていた場合（subject == null）
        if (subject == null) {
            Map<String, String> errors = new HashMap<>();
            errors.put("notfound", "この科目はすでに削除されています。");

            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", ""); // 名前は空欄でOK

            return "subject_update.jsp";
        }

        // ★ 通常時
        request.setAttribute("cd", subject.getCd());
        request.setAttribute("name", subject.getName());
        request.setAttribute("school", subject.getSchool());

        return "subject_update.jsp";
    }
}

