package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        int entYear = 0;
        if (f1 != null && !f1.equals("0") && !f1.isEmpty()) {
            entYear = Integer.parseInt(f1);
        }

        Map<String, String> errors = new HashMap<>();
        List<TestListSubject> list = null;

        // ✅ 共通エラーチェック（今回の追加）
        if (entYear == 0 || f2 == null || f2.equals("0") || f3 == null || f3.equals("0")) {
            errors.put("common", "入学年度とクラスと科目を指定してください");
        } else {

            Subject subject = new Subject();
            subject.setCd(f3);

            TestListSubjectDao dao = new TestListSubjectDao();
            list = dao.filter(entYear, f2, subject, teacher.getSchool());
        }

        // ✅ セレクトボックス用
        LocalDate now = LocalDate.now();
        int year = now.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", cNumDao.filter(teacher.getSchool()));
        request.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));

        // ✅ 値保持
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);

        request.setAttribute("errors", errors);
        request.setAttribute("test_list_subject", list);

        return "test_list_subject.jsp";
    }
}