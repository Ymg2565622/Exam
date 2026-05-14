package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDao extends Dao {
	
	public Subject get(String cd, School school) throws Exception {
		Subject subject = new Subject();
		String sql = "select * from subject where cd = ? and school_cd = ?";

		// 「Pre」を削除し、正しく修正しました
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, cd);
			statement.setString(2, school.getCd());
			
			try (ResultSet rSet = statement.executeQuery()) {
				SchoolDao schoolDao = new SchoolDao();

				if (rSet.next()) {
					subject.setCd(rSet.getString("cd"));
					subject.setName(rSet.getString("name"));
					subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
				} else {
					subject = null;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return subject;
	}
	
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list = new ArrayList<>();
		String sql = "select * from subject where school_cd = ?";

		// 「Pre」を削除し、正しく修正しました
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, school.getCd());
			
			try (ResultSet rSet = statement.executeQuery()) {
				SchoolDao schoolDao = new SchoolDao();

				while (rSet.next()) {
					Subject subject = new Subject();
					subject.setCd(rSet.getString("cd"));
					subject.setName(rSet.getString("name"));
					subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
					list.add(subject);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	public Boolean save(Subject subject) throws Exception {
		int count = 0;

		try (Connection connection = getConnection()) {
			Subject old = get(subject.getCd(), subject.getSchool());
			
			if (old == null) {
				// 該当科目が存在しない場合 INSERT
				String insertSql = "insert into subject(school_cd, cd, name) values(?, ?, ?)";
				try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
					statement.setString(1, subject.getSchool().getCd());
					statement.setString(2, subject.getCd());
					statement.setString(3, subject.getName());
					count = statement.executeUpdate();
				}
			} else {
				// 該当科目が存在する場合 UPDATE
				String updateSql = "update subject set name = ? where cd = ? and school_cd = ?";
				try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
					statement.setString(1, subject.getName());
					statement.setString(2, subject.getCd());
					statement.setString(3, subject.getSchool().getCd());
					count = statement.executeUpdate();
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return count > 0;
	}
	
	public Boolean delete(Subject subject) throws Exception {
		String sql = "DELETE FROM subject WHERE school_cd = ? AND cd = ?";
		int count = 0;

		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());
			
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		return count > 0;
	}
}
