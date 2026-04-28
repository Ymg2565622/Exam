package dao;

import java.util.List;

import bean.School;
import bean.Subject;

/**
 * コンパイルエラーを防ぐための仮実装
 * 後で正式版に置き換える
 */
public class SubjectDao extends Dao {
	
	
	public Subject get(String cd, School school) throws Exception {
		return null;
	}
	

	public List<Subject> filter(School school) throws Exception {
		return null;
	}
	

	public boolean save(Subject subject) throws Exception {
		return false;
	}
	

	public boolean delete(Subject subject) throws Exception {
		return false;
	}
	
}
