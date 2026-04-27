package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
