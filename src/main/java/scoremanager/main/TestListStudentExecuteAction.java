package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String f5 = request.getParameter("f5");

        Map<String, String> errors = new HashMap<>();
        List<TestListStudent> list = null;
        Student student = null;

        // 入力チェック
        if (f5 == null || f5.trim().isEmpty()) {
            errors.put("f5", "学生番号を入力してください");

        } else {

            StudentDao sDao = new StudentDao();
            student = sDao.get(f5);

            // 学生存在チェック
            if (student == null) {

            } else {

                TestListStudentDao dao = new TestListStudentDao();
                list = dao.filter(student);
            }
        }

        // セレクト用
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

        // （表示用）
        request.setAttribute("student", student);

        request.setAttribute("f5", f5);
        request.setAttribute("errors", errors);
        request.setAttribute("test_list_student", list);

        return "test_list_student.jsp";
    }
}