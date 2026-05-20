package scoremanager.main;

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

public class TestRegistAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ✅ パラメータ
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f4 = request.getParameter("f4");

        // ✅ DAO
        SubjectDao sDao = new SubjectDao();
        StudentDao studentDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        TestDao tDao = new TestDao();

        // ✅ プルダウン
        request.setAttribute("subject_set", sDao.filter(teacher.getSchool()));
        request.setAttribute("ent_year_set", studentDao.getEntYears(teacher.getSchool()));
        request.setAttribute("class_num_set", cNumDao.filter(teacher.getSchool()));

        List<Integer> noSet = new ArrayList<>();
        for (int i = 1; i <= 5; i++) noSet.add(i);
        request.setAttribute("no_set", noSet);

        // ✅ 条件一致
        if (f1 != null && f2 != null && f3 != null && f4 != null &&
            !f1.equals("0") && !f2.equals("0") &&
            !f3.equals("0") && !f4.equals("0")) {

            // ✅ 学生
            List<Student> studentList =
                studentDao.filter(
                    teacher.getSchool(),
                    Integer.parseInt(f1),
                    f2,
                    true
                );

            request.setAttribute("student_list", studentList);

            // ✅ ✅ ✅ デフォルト値（重要部分）
            Map<String, Integer> pointMap = new HashMap<>();

            Subject subject = new Subject();
            subject.setCd(f3);

            List<Test> testList = tDao.filter(
                teacher.getSchool(),
                f2,
                subject,
                Integer.parseInt(f4)
            );

            for (Test t : testList) {
                if (t.getStudent() != null) {
                    pointMap.put(
                        t.getStudent().getNo(),
                        t.getPoint()
                    );
                }
            }

            request.setAttribute("point_map", pointMap);

            return "test_regist.jsp";
        }

        return "test_regist.jsp";
    }
}
