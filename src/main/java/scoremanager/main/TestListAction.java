package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // =========================
        // パラメータ取得
        // =========================
        String mode = request.getParameter("mode"); // hidden
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f5 = request.getParameter("f5");

        int entYear = 0;

        if (f1 != null && !f1.equals("0")) {
            entYear = Integer.parseInt(f1);
        }

        Map<String, String> errors = new HashMap<>();
        List<Test> testList = null;

        // =========================
        // 分岐（ここが重要）
        // =========================
        if ("subject".equals(mode)) {

            // 科目検索
            if (entYear == 0) {
                errors.put("f1", "入学年度を選択してください");
            }
            if (f2 == null || f2.equals("0")) {
                errors.put("f2", "クラスを選択してください");
            }
            if (f3 == null || f3.equals("0")) {
                errors.put("f3", "科目を選択してください");
            }

            if (errors.isEmpty()) {

                Subject subject = new Subject();
                subject.setCd(f3);

                TestDao dao = new TestDao();

                int no = 1; // 仮

                testList = dao.filter(
                        teacher.getSchool(),
                        f2,
                        subject,
                        no
                );
            }

        } else if ("student".equals(mode)) {

            // 学生検索
            if (f5 == null || f5.trim().isEmpty()) {
                errors.put("f5", "学生番号を入力してください");
            }

            if (errors.isEmpty()) {

                StudentDao sDao = new StudentDao();
                Student student = sDao.get(f5);

                if (student == null) {
                    errors.put("f5", "学生が存在しません");
                } else {
                    // 仮（後でDAO拡張）
                    testList = new ArrayList<>();
                }
            }
        }

        // =========================
        // セレクトボックス用データ
        // =========================
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", cNumDao.filter(teacher.getSchool()));
        request.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));

        // =========================
        // 値保持
        // =========================
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);
        request.setAttribute("f5", f5);

        // =========================
        // 結果・エラー
        // =========================
        request.setAttribute("errors", errors);
        request.setAttribute("test_list", testList);

        return "test_list.jsp";
    }
}
