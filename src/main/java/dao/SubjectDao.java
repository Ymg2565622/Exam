package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	
	
	/***
	 * 科目コード、学校情報を元に科目情報を返却
	 * 
	 * @param cd
	 * @param school
	 * @return
	 * @throws Exception
	 */
	public Subject get(String cd, School school) throws Exception {
		Subject subject = new Subject();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("select * from subject where cd = ? and school_cd = ?");
			statement.setString(1, cd);
			statement.setString(2, school.getCd());
			ResultSet rs = statement.executeQuery();
			
			SchoolDao schoolDao = new SchoolDao();
			if (rs.next()) {
				subject.setCd(rs.getString("cd"));
				subject.setName(rs.getString("name"));
				subject.setSchool(schoolDao.get(rs.getString("school_cd")));
			} else {
				//該当データが存在しなければnullをセット
				subject = null;
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
		
		return subject;
		
	}
	
	/***
	 * 学校を指定して科目の一覧を取得
	 * 
	 * @param school
	 * @return
	 * @throws Exception
	 */
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(
					"select * from subject where school_cd = ? order by cd");
			statement.setString(1, school.getCd());
			ResultSet rs = statement.executeQuery();
			
			SchoolDao schoolDao = new SchoolDao();
			
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setCd(rs.getString("cd"));
				subject.setName(rs.getString("name"));
				subject.setSchool(schoolDao.get(rs.getString("school_cd")));
				list.add(subject);
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
	
	/***
	 * 科目インスタンスを、subjectテーブルに保存する。
	 * 既に同じ科目コードと学校コードのデータが存在する場合は更新、存在しない場合は登録
	 * 
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public boolean save(Subject subject) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			Subject old = get(subject.getCd(), subject.getSchool());
			if (old == null) {
				//該当科目が存在しない場合 INSERT
				statement = connection.prepareStatement(
						"insert into subject(school_cd, cd, name)"
						+ " values(?, ?, ?)");
				statement.setString(1, subject.getSchool().getCd());
				statement.setString(2, subject.getCd());
				statement.setString(3, subject.getName());
			} else {
				//該当科目が存在する場合 UPDATE
				statement = connection.prepareStatement(
						"update subject set name=? where school_cd=? and cd=?");
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getSchool().getCd());
				statement.setString(3, subject.getCd());
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
	 * 科目インスタンスを参照して、subjectテーブルから同じ科目データを削除する
	 * 
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Subject subject) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			statement = connection.prepareStatement(
					"delete from subject "
					+ "where school_cd=? and cd=?");
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());
			
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
