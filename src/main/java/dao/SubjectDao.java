package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

/**
 * テスト用
 * 後で正式なもので置き換える
 */
public class SubjectDao extends Dao {

    /**
     * 主キー（school_cd, cd）で科目を1件取得
     * 既に存在するかどうかのチェック用
     */
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;

        String sql =
            "SELECT school_cd, cd, name " +
            "FROM subject " +
            "WHERE school_cd = ? AND cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setString(2, cd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setSchool(school);
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
            }
        }

        return subject;
    }

    /**
     * 学校ごとの科目一覧取得
     */
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        String sql =
            "SELECT school_cd, cd, name " +
            "FROM subject " +
            "WHERE school_cd = ? " +
            "ORDER BY cd";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSchool(school);
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));

                list.add(subject);
            }
        }

        return list;
    }

    /**
     * 科目登録
     */
    public boolean save(Subject subject) throws Exception {

        String sql =
            "INSERT INTO subject (school_cd, cd, name) " +
            "VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getSchool().getCd());
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getName());

            int count = ps.executeUpdate();
            return count == 1;
        }
    }

    /**
     * 科目削除
     */
    public boolean delete(Subject subject) throws Exception {

        String sql =
            "DELETE FROM subject " +
            "WHERE school_cd = ? AND cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getSchool().getCd());
            ps.setString(2, subject.getCd());

            int count = ps.executeUpdate();
            return count == 1;
        }
    }
}