package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	
	private String baseSql = "select * from student where school_cd=?";
	
	/***
	 * 学生番号を元に、一致する学生データをstudentテーブルから取得する
	 * 
	 * @param no
	 * @return
	 * @throws Exception
	 */
	public Student get(String no) throws Exception{
		Student student = new Student();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("select * from student where no=?");
			statement.setString(1, no);
			ResultSet rs = statement.executeQuery();
			
			SchoolDao schoolDao = new SchoolDao();
			if (rs.next()) {
				student.setNo(rs.getString("no"));
				student.setName(rs.getString("name"));
				student.setEntYear(rs.getInt("ent_year"));
				student.setClassNum(rs.getString("class_num"));
				student.setAttend(rs.getBoolean("is_attend"));
				student.setSchool(schoolDao.get(rs.getString("school_cd")));
			} else {
				//該当データが存在しなければnullをセット
				student = null;
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
		
		return student;
		
		
	}
	
	/***
	 * 各filter後の結果を生徒インスタンスのリストに格納する
	 * 
	 * @param resultSet
	 * @param school
	 * @return
	 * @throws Exception
	 */
	private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
		List<Student> list = new ArrayList<>();
		try {
			while (resultSet.next()) {
				//学生データをインスタンスに格納し、リストに追加
				Student student = new Student();
				student.setNo(resultSet.getString("no"));
				student.setName(resultSet.getString("name"));
				student.setEntYear(resultSet.getInt("ent_year"));
				student.setClassNum(resultSet.getString("class_num"));
				student.setAttend(resultSet.getBoolean("is_attend"));
				student.setSchool(school);
				list.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/***
	 *  学校、入学年度、クラス番号、在学フラグを指定して学生の一覧を取得
	 * 
	 * @param school
	 * @param entYear
	 * @param classNum
	 * @param isAttend
	 * @return
	 * @throws Exception
	 */
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//SQL文の設定用
		String condition = " and ent_year=? and class_num=?";
		String order = " order by no asc";
		String conditionIsAttend = "";
		
		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}
		
		try {
			statement = connection.prepareStatement((baseSql) + condition + conditionIsAttend + order);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
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
	 * 学校、入学年度、在学フラグを指定して学生の一覧を取得
	 * 
	 * @param school
	 * @param entYear
	 * @param isAttend
	 * @return
	 * @throws Exception
	 */
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//SQL文の設定用
		String condition = " and ent_year=?";
		String order = " order by no asc";
		String conditionIsAttend = "";
		
		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}
		
		try {
			statement = connection.prepareStatement((baseSql) + condition + conditionIsAttend + order);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
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
	 * 学校、在学フラグを指定して学生の一覧を取得
	 * 
	 * @param school
	 * @param isAttend
	 * @return
	 * @throws Exception
	 */
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//SQL文の設定用
		String order = " order by no asc";
		String conditionIsAttend = "";
		
		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}
		
		try {
			statement = connection.prepareStatement((baseSql) + conditionIsAttend + order);
			statement.setString(1, school.getCd());
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
	 * 学生インスタンスを、studentテーブルに保存する。
	 * 既に同じ学生番号のデータが存在する場合は更新、存在しない場合は登録
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public boolean save(Student student) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			Student old = get(student.getNo());
			if (old == null) {
				//該当学生が存在しない場合 INSERT
				statement = connection.prepareStatement(
						"insert into student(no, name, ent_year, class_num, is_attend, school_cd)"
						+ " values(?, ?, ?, ?, ?, ?)");
				statement.setString(1, student.getNo());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.isAttend());
				statement.setString(6, student.getSchool().getCd());
			} else {
				//該当学生が存在する場合 UPDATE
				statement = connection.prepareStatement(
						"update student set name=?, ent_year=?, class_num=?, is_attend=? "
						+ "where no=?");
				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.isAttend());
				statement.setString(5, student.getNo());
			}
			
			count = statement.executeUpdate();
			
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

		if (count > 0) {
			//実行回数が1件以上
			return true;
		} else {
			//実行回数が0件
			return false;
		}
	}
	
	/***
	 * 学校に所属する全学生の入学年度を重複なく取得
	 * (成績参照時の入学年度リストに使用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getEntYears(School school) throws Exception {
		List<Integer> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
	
		try {
			statement = connection.prepareStatement(
					"select distinct ent_year from student where school_cd=?");
			statement.setString(1, school.getCd());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("ent_year"));
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
		
		return list;
		
	}
}
