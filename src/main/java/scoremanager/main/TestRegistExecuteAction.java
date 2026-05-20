package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 成績登録処理（完成版）
 */
public class TestRegistExecuteAction extends Action {

    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // ✅ セッション取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ✅ パラメータ取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f4 = request.getParameter("f4");

        Map<String, String> errors = new HashMap<>();

        // ✅ 入力チェック
        if (f1 == null || f1.equals("0")) {
            errors.put("f1", "入学年度を選択してください");
        }
        if (f2 == null || f2.equals("0")) {
            errors.put("f2", "クラスを選択してください");
        }
        if (f3 == null || f3.equals("0")) {
            errors.put("f3", "科目を選択してください");
        }
        if (f4 == null || f4.equals("0")) {
            errors.put("f4", "回数を選択してください");
        }

        // ✅ エラー時
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            return "test_regist.jsp";
        }

        // ✅ 学生一覧取得
        StudentDao studentDao = new StudentDao();
        List<Student> studentList =
                studentDao.filter(
                        teacher.getSchool(),
                        Integer.parseInt(f1),
                        f2,
                        true
                );

        // ✅ テストデータ作成
        List<Test> testList = new ArrayList<>();

        for (Student student : studentList) {

            String studentNo = student.getNo();
            String pointStr = request.getParameter("point_" + studentNo);

            // ✅ 未入力はスキップ
            if (pointStr == null || pointStr.isEmpty()) {
                continue;
            }

            int point;

            try {
                point = Integer.parseInt(pointStr);
            } catch (NumberFormatException e) {
                continue;
            }

            // ✅ 範囲チェック
            if (point < 0 || point > 100) {
                continue;
            }

            Test test = new Test();

            // ✅ Student
            test.setStudent(student);

            // ✅ Subject
            Subject subject = new Subject();
            subject.setCd(f3);
            test.setSubject(subject);

            // ✅ 回数
            test.setNo(Integer.parseInt(f4));

            // ✅ 点数
            test.setPoint(point);

            // ✅ 学校
            test.setSchool(teacher.getSchool());

            // ✅ クラス
            test.setClassNum(student.getClassNum());

            // ✅ リスト追加
            testList.add(test);
        }

        // ✅ ★重要：データ0件対策
        if (testList.isEmpty()) {
            request.setAttribute("error", "点数を1件以上入力してください");
            request.setAttribute("student_list", studentList); // 再表示用
            return "test_regist.jsp";
        }

        // ✅ DB保存
        TestDao testDao = new TestDao();
        testDao.save(testList);

        // ✅ 完了画面
        return "test_regist_done.jsp";
    }
}
