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
     * 成績を1件取得
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;

        Connection con = getConnection();

        String sql =
            "SELECT * FROM test " +
            "WHERE student_no = ? " +
            "AND subject_cd = ? " +
            "AND school_cd = ? " +
            "AND no = ?";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, student.getNo());
        st.setString(2, subject.getCd());
        st.setString(3, school.getCd());
        st.setInt(4, no);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            test = new Test();

            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));
            test.setClassNum(rs.getString("class_num"));
        }

        st.close();
        con.close();

        return test;
    }

    /**
     * 成績一覧取得
     */
    public List<Test> filter(School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT * FROM test " +
            "WHERE school_cd = ? " +
            "ORDER BY class_num, student_no";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Test test = new Test();

            Student student = new Student();
            student.setNo(rs.getString("student_no"));

            Subject subject = new Subject();
            subject.setCd(rs.getString("subject_cd"));

            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));
            test.setClassNum(rs.getString("class_num"));

            list.add(test);
        }

        st.close();
        con.close();

        return list;
    }

    /**
     * 成績登録
     */
    public boolean save(Test test) throws Exception {

        Connection con = getConnection();

        String sql =
            "INSERT INTO test " +
            "(student_no, subject_cd, school_cd, no, point, class_num) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSchool().getCd());
        st.setInt(4, test.getNo());
        st.setInt(5, test.getPoint());
        st.setString(6, test.getClassNum());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }

    /**
     * 成績更新
     */
    public boolean update(Test test) throws Exception {

        Connection con = getConnection();

        String sql =
            "UPDATE test SET point = ?, class_num = ? " +
            "WHERE student_no = ? " +
            "AND subject_cd = ? " +
            "AND school_cd = ? " +
            "AND no = ?";

        PreparedStatement st = con.prepareStatement(sql);

        st.setInt(1, test.getPoint());
        st.setString(2, test.getClassNum());
        st.setString(3, test.getStudent().getNo());
        st.setString(4, test.getSubject().getCd());
        st.setString(5, test.getSchool().getCd());
        st.setInt(6, test.getNo());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }

    /**
     * 成績削除
     */
    public boolean delete(Test test) throws Exception {

        Connection con = getConnection();

        String sql =
            "DELETE FROM test " +
            "WHERE student_no = ? " +
            "AND subject_cd = ? " +
            "AND school_cd = ? " +
            "AND no = ?";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSchool().getCd());
        st.setInt(4, test.getNo());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }
}
