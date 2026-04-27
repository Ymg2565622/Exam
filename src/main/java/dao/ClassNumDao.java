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
	 * クラス図に記述があるが、クラス管理については実装しないため未実装
	 */
	public boolean save(ClassNum classNum) throws Exception {
		return false;
		
	}
	
	/**
	 * クラス図に記述があるが、クラス管理については実装しないため未実装
	 */
	public boolean save(ClassNum classNum, String newClassNum) throws Exception {
		return false;	
	}
}
