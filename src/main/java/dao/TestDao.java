package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
	
	private String baseSql = """
			select 
				test.student_no,
				test.subject_no,
				test.school_cd,
				test.no,
				test.point,
				test.class_num
			from 
				test 
			inner join 
				student 
			on 
				test.student_no = student.no
			where 
			""";
	
	public Test get(Student student, Subject subject, School school, int no) throws Exception{
		Test test = new Test();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		String condition = """
				test.student_no=? and
				test.subject_no=? and
				test.school_cd=? and
				test.no=?
				""";
		String order = " order by test.student_no asc";
		
		try {
			statement = connection.prepareStatement((baseSql) + condition + order);
			statement.setString(1, student.getNo());
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			ResultSet rs = statement.executeQuery();
			
			SchoolDao schoolDao = new SchoolDao();
			StudentDao studentDao = new StudentDao();
			SubjectDao subjectDao = new SubjectDao();
			if (rs.next()) {
				test.setStudent(studentDao.get(rs.getString("student_no")));
				test.setClassNum(rs.getString("class_num"));
				test.setSubject(subjectDao.get(rs.getString("subject_no"), school));
				test.setSchool(schoolDao.get(rs.getString("school_cd")));
				test.setNo(rs.getInt("no"));
				test.setPoint(rs.getInt("point"));
			} else {
				//該当データが存在しなければnullをセット
				test = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（PreparedStatemnt, Connection）を安全に閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		return test;
		
		
	}
	
	/***
	 * 各filter後の結果を成績インスタンスのリストに格納する
	 * 
	 * @param resultSet
	 * @param school
	 * @return
	 * @throws Exception
	 */
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		//各種Dao
		StudentDao studentDao = new StudentDao();
		SubjectDao  subjectDao = new SubjectDao();
		try {
			while (rSet.next()) {
				//インスタンスの値をセットしてリストに保存
				Test test = new Test();
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setClassNum(rSet.getString("class_num"));
				test.setSubject(subjectDao.get(rSet.getString("subject_no"), school));
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/***
	 *  学校、入学年度、クラス番号、科目、回数指定して成績の一覧を取得
	 * 
	 */
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//SQL文の設定用
		String condition = """
				student.ent_year=? and
				test.class_num=? and
				test.subject_no=? and
				test.no=? and
				test.school_cd=?
				""";
		String order = " order by test.student_no asc";
		
		try {
			statement = connection.prepareStatement((baseSql) + condition + order);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setInt(4, num);
			statement.setString(5, school.getCd());
			resultSet = statement.executeQuery();
			list = postFilter(resultSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（PreparedStatemnt, Connection）を安全に閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		
		return list;
	}
	
	/***
	 *  学校、入学年度、クラス番号、科目指定して成績の一覧を取得
	 */
	public List<Test> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//SQL文の設定用
		String condition = """
				student.ent_year=? and
				test.class_num=? and
				test.subject_no=? and
				test.school_cd=?
				""";
		String order = " order by test.student_no asc";
		
		try {
			statement = connection.prepareStatement((baseSql) + condition + order);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());
			resultSet = statement.executeQuery();
			list = postFilter(resultSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（PreparedStatemnt, Connection）を安全に閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		return list;
	}
	
	/***
	 *  学生指定して成績の一覧を取得
	 */
	public List<Test> filter(Student student) throws Exception {
		List<Test> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//SQL文の設定用
		String condition = """
				test.student_no=?
				""";
		String order = " order by test.student_no asc";
		
		try {
			statement = connection.prepareStatement((baseSql) + condition + order);
			statement.setString(1, student.getNo());
			resultSet = statement.executeQuery();
			list = postFilter(resultSet, student.getSchool());
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（PreparedStatemnt, Connection）を安全に閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		return list;
	}
	
	/***
	 * 成績インスタンスのリストを、testテーブルに保存する。
	 * 既に同じ対象のデータが存在する場合は更新、存在しない場合は登録
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean save(List<Test> list) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		//処理が失敗したかチェックするフラグ
		boolean isFailed = false;
		
		try {
			for (Test test: list) {
				save(test, connection);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（Connection)を安全に閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		return isFailed;
	}
	
	private boolean save(Test test, Connection connection) throws Exception {
		//DB処理に必要なリソースの確立
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
			if (old == null) {
				//該当成績が存在しない場合 INSERT
				statement = connection.prepareStatement(
						"insert into test(student_no, subject_no, school_cd, no, point, class_num)"
						+ " values(?, ?, ?, ?, ?, ?)");
				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchool().getCd());	
				statement.setInt(4, test.getNo());	
				statement.setInt(5, test.getPoint());
				statement.setString(6, test.getClassNum());
			} else {
				//該当成績が存在する場合 UPDATE
				statement = connection.prepareStatement(
						"update test set point=? "
						+ "where student_no=? and subject_no=? and school_cd=? and no=?");
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getNo());
			}
			
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			//使用したリソース（PreparedStatemnt）を安全に閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			//実行回数が1件以上
			return true;
		} else {
			//実行回数が0件
			return false;
		}
	}
}
