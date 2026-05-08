package dao;

import java.util.ArrayList;
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
		List<Subject> list = new ArrayList<>();
		
		//テスト用データを作成
		Subject subject = new Subject();
		subject.setSchool(school);
		subject.setCd("ts1");
		subject.setName("テスト用科目");
		
		list.add(subject);
		
		return list;
	}
	

	public boolean save(Subject subject) throws Exception {
		return false;
	}
	

	public boolean delete(Subject subject) throws Exception {
		return false;
	}
	
}
