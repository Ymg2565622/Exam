package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    /**
     * 成績1件取得
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;

        Connection con = getConnection();

        String sql =
            "SELECT * FROM test " +
            "WHERE student_no = ? " +
            "AND subject_cd = ? " +
            "AND no = ? " +
            "AND school_cd = ?"; // ✅ 修正

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, student.getNo());
        st.setString(2, subject.getCd());
        st.setInt(3, no);
        st.setString(4, school.getCd()); // ✅ 修正

        ResultSet rs = st.executeQuery();

        if (rs.next()) {

            test = new Test();

            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(student.getSchool());
            test.setClassNum(rs.getString("class_num"));
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));
        }

        st.close();
        con.close();

        return test;
    }

    /**
     * 成績一覧取得
     */
    public List<Test> filter(School school, String classNum, Subject subject, int no)
            throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT * FROM test " +
            "WHERE school_cd = ? " +
            "AND class_num = ? " +
            "AND subject_cd = ? " +
            "AND no = ? " +
            "ORDER BY student_no";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, school.getCd());
        st.setString(2, classNum);
        st.setString(3, subject.getCd());
        st.setInt(4, no);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Test test = new Test();

            Student student = new Student();
            student.setNo(rs.getString("student_no"));

            Subject sub = new Subject();
            sub.setCd(rs.getString("subject_cd"));

            test.setStudent(student);
            test.setSubject(sub);
            test.setSchool(school);
            test.setClassNum(rs.getString("class_num"));
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));

            list.add(test);
        }

        st.close();
        con.close();

        return list;
    }

    /**
     * 成績保存（INSERT / UPDATE）
     */
    public boolean save(List<Test> list) throws Exception {

        if (list == null || list.isEmpty()) {
            return false; // ✅ 安全対策
        }

        Connection con = getConnection();

        int count = 0;

        for (Test test : list) {

            // ✅ 修正①：school_cd追加
            String checkSql =
                "SELECT COUNT(*) FROM test " +
                "WHERE student_no = ? " +
                "AND subject_cd = ? " +
                "AND no = ? " +
                "AND school_cd = ?";

            PreparedStatement checkSt = con.prepareStatement(checkSql);

            checkSt.setString(1, test.getStudent().getNo());
            checkSt.setString(2, test.getSubject().getCd());
            checkSt.setInt(3, test.getNo());
            checkSt.setString(4, test.getSchool().getCd()); // ✅ 追加

            ResultSet rs = checkSt.executeQuery();
            rs.next();

            boolean exists = rs.getInt(1) > 0;

            checkSt.close();

            PreparedStatement st;

            if (exists) {

                // ✅ 修正②：UPDATEにもschool_cd追加
                String updateSql =
                    "UPDATE test SET point = ? " +
                    "WHERE student_no = ? " +
                    "AND subject_cd = ? " +
                    "AND no = ? " +
                    "AND school_cd = ?";

                st = con.prepareStatement(updateSql);

                st.setInt(1, test.getPoint());
                st.setString(2, test.getStudent().getNo());
                st.setString(3, test.getSubject().getCd());
                st.setInt(4, test.getNo());
                st.setString(5, test.getSchool().getCd()); // ✅ 追加

            } else {

                // ✅ INSERT（ここは元からOK）
                String insertSql =
                    "INSERT INTO test(" +
                    "student_no, subject_cd, school_cd, class_num, no, point" +
                    ") VALUES (?, ?, ?, ?, ?, ?)";

                st = con.prepareStatement(insertSql);

                st.setString(1, test.getStudent().getNo());
                st.setString(2, test.getSubject().getCd());
                st.setString(3, test.getSchool().getCd());
                st.setString(4, test.getClassNum());
                st.setInt(5, test.getNo());
                st.setInt(6, test.getPoint());
            }

            count += st.executeUpdate();

            st.close();
        }

        con.close();

        return count > 0;
    }
}
