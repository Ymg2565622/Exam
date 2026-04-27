package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		//TestDaoから取得した成績インスタンスを成績参照（クラス、科目）用データに変換する
		List<TestListSubject> list = new ArrayList<>();
		
		//成績インスタンスの取得用Dao
		TestDao testDao = new TestDao();
		//条件に該当する成績インスタンスを取得
		List<Test> testList = testDao.filter(entYear, classNum, subject, school);
		//同じ学生番号の点数をまとめて、参照用インスタンスに変換する処理
		//学生番号順にソート(DBでされているが念のため)
		testList.sort(Comparator.comparing(t -> t.getStudent().getNo()));
		
	    Student lastStudent = null;
	    TestListSubject current = null;

	    for (Test test : testList) {
	        Student student = test.getStudent();
	        // 学生が変わったら新規作成
	        if (lastStudent == null || !lastStudent.getNo().equals(student.getNo())) {
	            current = new TestListSubject();
	            current.setEntYear(entYear);
	            current.setStudentNo(student.getNo());
	            current.setStudentName(student.getName());
	            current.setClassNum(classNum);
	            current.setPoints(new HashMap<>());
	            //リストに追加
	            list.add(current);
	        }

	        // 点数追加
	        current.putPoint(test.getNo(), test.getPoint());

	        lastStudent = student;
	    }
		return list;
	}
	
}
