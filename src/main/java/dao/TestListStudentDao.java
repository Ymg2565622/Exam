package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    // 学生の成績データを取得する SQL
    private String sql ="""
            SELECT 
            	subject.name AS subject_name, 
            	subject.cd AS subject_cd, 
            	test.no,
            	test.point 
            FROM test 
            JOIN subject 
            	ON test.subject_cd = subject.cd 
            WHERE test.student_no = ? 
            ORDER BY subject.cd, test.no
            """;

public List<TestListStudent> filter(Student student) throws Exception {
	List<TestListStudent> list = new ArrayList<>();
	
	Connection connection = getConnection();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	
	try (Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

           ps.setString(1, student.getNo());  // Student.no をセット

           ResultSet rs = ps.executeQuery();
           list = postFilter(rs);

       } catch (Exception e) {
           e.printStackTrace();
       }

       return list;
   }

private List<TestListStudent> postFilter(ResultSet rs) throws Exception {
    List<TestListStudent> list = new ArrayList<>();

    while (rs.next()) {
        TestListStudent test = new TestListStudent();
        test.setSubjectCd(rs.getString("subject_cd"));     // AS subject_cd
        test.setSubjectName(rs.getString("subject_name")); // AS subject_name
        test.setNum(rs.getInt("no"));                      // DB カラム no
        test.setPoint(rs.getInt("point"));

        list.add(test);
    }

    return list;
	}
}