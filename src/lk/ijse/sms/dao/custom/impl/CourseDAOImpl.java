/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.CourseDAO;
import lk.ijse.sms.entity.Course;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean save(Course entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO course VALUES (?,?,?)", entity.getCourse_id(), entity.getCourse_title(), entity.getDuration());
    }

    @Override
    public boolean update(Course entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE course SET course_title = ?, duration = ? WHERE course_id = ?", entity.getCourse_title(), entity.getDuration(), entity.getCourse_id());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM course WHERE course_id = ?", id);
    }

    @Override
    public Course findByID(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM course WHERE course_id = ?", id);
        if (rs.next()) {
            return new Course(rs.getString(1), rs.getString(2), rs.getString(3));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Course> getAll() throws Exception {
        ArrayList<Course> allCourses = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM course");
        while (rs.next()) {
            allCourses.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return allCourses;
    }

    @Override
    public String getIDByTitle(String courseTitle) throws Exception {

        ResultSet rs = CrudUtil.executeQuery("SELECT course_id FROM course WHERE course_title = ?", courseTitle);
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }

    }

    @Override
    public String getLastID() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT course_id FROM  course ORDER BY course_id DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public int CourseCount() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT COUNT(*) FROM course");
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public String courseId(String Batch_no) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select c.course_id FROM course c INNER JOIN batch b ON c.course_id=b.course_id where b.batch_no=?", Batch_no);
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

}
