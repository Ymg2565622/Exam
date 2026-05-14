package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	private TestListSubject postFilter(ResultSet rs) throws SQLException {

	    TestListSubject bean = new TestListSubject();

	    bean.setEntYear(rs.getInt("ent_year"));
	    bean.setClassNum(rs.getString("class_num"));
	    bean.setStudentNo(rs.getString("student_no"));
	    bean.setStudentName(rs.getString("student_name"));

	    return bean;
	}
	
	
	/***
	 *  入学年度、クラス番号、科目、学校を指定して学生の一覧を取得
	 * 
	 * @param entYear 
	 * @param classNum
	 * @param subject
	 * @param school
	 * @return
	 * @throws Exception
	 */
	
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school ) throws Exception {
		List<TestListSubject> list = new ArrayList<>();
		
		//DB処理に必要なリソースの確立
		Connection connection1 = getConnection();
		PreparedStatement statement1 = null;
		ResultSet resultSet = null;
		
		//SQL文の設定用
		String condition = ("select " +
						"s.ent_year, " +
						"t.class_num, " +
						"t.student_no, " +
						"s.name as  student_name, " +
						"t.no as test_no, " +
						"t.point as point " +
						"from test t " +
						"join student s " +
						"on t.student_no = s.no " +
						"where t.school_cd = ? " +
						"and s.ent_year = ? " +
						"and t.class_num = ? " +
						"and t.subject_cd = ? ");
		String order = (" order by t.student_no asc");

		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(condition + order);
				
				){
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			statement.setString(4, subject.getCd());
			
			TestListSubject bean = null;
			String currentStudentNo = null;
			
			try(ResultSet rs = statement.executeQuery()){
				
				while(rs.next()) {
					
					String studentNo = rs.getString("student_no");
					
					//学生が変わったら新しくBean作成
					if(!studentNo.equals(currentStudentNo)) {
						
						bean = postFilter(rs);
						
						list.add(bean);
						
						currentStudentNo = studentNo;
					}
					
					//テスト回数と点数をMapへ入れる
					int testNo = rs.getInt("test_no");
					int point = rs.getInt("point");
					
					bean.putPoint(testNo, point);
				}
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（PreparedStatemnt1, Connection1）を安全に閉じる
			if (statement1 != null) {
				try {
					statement1.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection1 != null) {
				try {
					connection1.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		return list;
		
	}
	
}
