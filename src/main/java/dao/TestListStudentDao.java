package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Test;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
	
	public List<TestListStudent> filter(Student student) throws Exception {
		//TestDaoから取得した成績インスタンスを成績参照（学生）用データに変換する
		List<TestListStudent> list = new ArrayList<>();
		
		//成績インスタンスの取得用Dao
		TestDao testDao = new TestDao();
		//条件に該当する成績インスタンスを取得
		List<Test> testList = testDao.filter(student);
		//成績参照（学生）用に変換
	    for (Test test : testList) {
	    	TestListStudent testListStudent = new TestListStudent();
	    	testListStudent.setNum(test.getNo());
	    	testListStudent.setPoint(test.getPoint());
	    	testListStudent.setSubjectCd(test.getSubject().getCd());
	    	testListStudent.setSubjectName(test.getSubject().getName());
	    	list.add(testListStudent);
	    }
		return list;
	}
	
}
