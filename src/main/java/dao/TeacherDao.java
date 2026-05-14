package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {
	
	/***
	 * 教員IDをキーにteacherテーブルから教員データを1件取得
	 * 
	 * @param id 教員ID
	 * @return 該当する教員データ。存在しない場合はnull
	 * @throws Exception
	 */
	public Teacher get(String id) throws Exception {
		Teacher teacher = new Teacher();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from teacher where id=?");
			statement.setString(1, id);
			ResultSet rSet = statement.executeQuery();
			
			//学校コードから学校データを得る用のDaoを初期化
			SchoolDao schoolDao = new SchoolDao();
			
			if (rSet.next()) {
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				//取得した学校コードを元に学校データを取得し学校パラメータに格納
				teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				//該当データが存在しない場合はnull
				teacher = null;
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
		
		return teacher;
		
	}
	
	/***
	 * 教員IDとパスワードをキーにteacherテーブルから教員データを1件取得する。
	 * 取得の成否をログイン認証として利用する。
	 * 
	 * @param id 教員ID password パスワード
	 * @return 該当する教員データ。存在しない場合はnull
	 * @throws Exception
	 */
	public Teacher login(String id, String password) throws Exception {
		Teacher teacher = new Teacher();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from teacher where id=? and password=?");
			statement.setString(1, id);
			statement.setString(2, password);
			ResultSet rSet = statement.executeQuery();
			
			//学校コードから学校データを得る用のDaoを初期化
			SchoolDao schoolDao = new SchoolDao();
			
			if (rSet.next()) {
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				//取得した学校コードを元に学校データを取得し学校パラメータに格納
				teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				//該当データが存在しない場合はnull
				teacher = null;
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
		
		return teacher;
		
	}
	
	//ユーザー管理用に学校ごとのリスト取得、更新、登録を追加
	
	public List<Teacher> filter(School school) throws Exception {
		List<Teacher> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(
					"select * from teacher where school_cd = ? order by id");
			statement.setString(1, school.getCd());
			ResultSet rs = statement.executeQuery();
			//学校コードから学校データを得る用のDaoを初期化
			SchoolDao schoolDao = new SchoolDao();
			
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getString("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				//取得した学校コードを元に学校データを取得し学校パラメータに格納
				teacher.setSchool(schoolDao.get(rs.getString("school_cd")));
				list.add(teacher);
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
	
	public boolean save(Teacher teacher) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			Teacher old = get(teacher.getId());
			if (old == null) {
				//該当学生が存在しない場合 INSERT
				statement = connection.prepareStatement(
						"insert into teacher(id, password, name, school_cd)"
						+ " values(?, ?, ?, ?)");
				statement.setString(1, teacher.getId());
				statement.setString(2, teacher.getPassword());
				statement.setString(3, teacher.getName());
				statement.setString(4, teacher.getSchool().getCd());
			} else {
				//該当学生が存在する場合 UPDATE
				statement = connection.prepareStatement(
						"update teacher set password=?, name=?, school_cd=? "
						+ "where id=?");
				statement.setString(1, teacher.getPassword());
				statement.setString(2, teacher.getName());
				statement.setString(3, teacher.getSchool().getCd());
				statement.setString(4, teacher.getId());
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
	
}
