package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
	
	
	/***
	 * クラス番号、学校情報を元にクラス情報を返却
	 * 
	 * @param class_num
	 * @param school
	 * @return
	 * @throws Exception
	 */
	public ClassNum get(String class_num, School school) throws Exception {
		ClassNum classNum = new ClassNum();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("select * from class_num where class_num = ? and school_cd = ?");
			statement.setString(1, class_num);
			statement.setString(2, school.getCd());
			ResultSet rs = statement.executeQuery();
			
			SchoolDao schoolDao = new SchoolDao();
			if (rs.next()) {
				classNum.setClass_num(rs.getString("class_num"));
				classNum.setSchool(schoolDao.get(rs.getString("school_cd")));
			} else {
				//該当データが存在しなければnullをセット
				classNum = null;
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
		
		return classNum;
		
	}
	
	/***
	 * 学校を指定してクラス番号の一覧を取得
	 * 
	 * @param school
	 * @return
	 * @throws Exception
	 */
	public List<String> filter(School school) throws Exception {
		List<String> list = new ArrayList<>();
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(
					"select class_num from class_num where school_cd = ? order by class_num");
			statement.setString(1, school.getCd());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("class_num"));
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
	
	/**
	 * クラス番号インスタンスを元にclass_numテーブルに新規登録する
	 * 
	 * @param classNum
	 * @return
	 * @throws Exception
	 */
	public boolean save(ClassNum classNum) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			statement = connection.prepareStatement("""
					insert into class_num (
						school_cd,
						class_num) 
					values(
						?,
						?)
					""");
			statement.setString(1, classNum.getSchool().getCd());
			statement.setString(2, classNum.getClass_num());
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
	
	/**
	 * クラス番号インスタンスから、class_numテーブルの該当データのクラス番号を新しいクラス番号に更新する
	 * 
	 * @param classNum
	 * @param newClassNum
	 * @return
	 * @throws Exception
	 */
	public boolean save(ClassNum classNum, String newClassNum) throws Exception {
		//DB処理に必要なリソースの確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行回数 
		int count = 0;
		
		try {
			statement = connection.prepareStatement("""
					update 
						class_num 
					set 
						class_num=?
					where 
						school_cd=? and 
						class_num=?
					""");
			statement.setString(1, newClassNum);
			statement.setString(2, classNum.getSchool().getCd());
			statement.setString(3, classNum.getClass_num());
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
