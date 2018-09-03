/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.StudentDAO;
import lk.ijse.sms.entity.Student;
import lk.ijse.sms.entity.StudentCourse;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO student VALUES (?,?,?,?,?,?)", entity.getSid(), entity.getFname(), entity.getLname(), entity.getNic(), entity.getContact(), entity.getAddress());
    }

    @Override
    public boolean update(Student entity) throws Exception {

        return CrudUtil.executeUpdate("UPDATE student SET fname = ?,lname = ? ,nic = ?,contact = ? ,address = ? WHERE sid = ?", entity.getFname(), entity.getLname(), entity.getNic(), entity.getContact(), entity.getAddress(), entity.getSid());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM student WHERE sid = ?", id);
    }

    @Override
    public Student findByID(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM student WHERE sid = ?", id);
        if (rs.next()) {

            return new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Student> getAll() throws Exception {
        ArrayList<Student> getall = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM student");
        while (rs.next()) {

            getall.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        return getall;
    }

    @Override
    public String getLastID() throws Exception {
       ResultSet rs = CrudUtil.executeQuery("SELECT sid FROM student ORDER BY sid DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<StudentCourse> getStudentCourses(String sid) throws Exception {
        ArrayList<StudentCourse> studentCourses = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT sb.batch_no,c.course_title from student_batch sb INNER JOIN student s on sb.sid = s.sid INNER JOIN batch b ON sb.batch_no=b.batch_no INNER JOIN course c ON b.course_id = c.course_id WHERE s.sid=?", sid);
        while (rs.next()) {

            studentCourses.add(new StudentCourse(rs.getString(2), rs.getString(1)));

        }
        return studentCourses;
    }

    @Override
    public int studentCount() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT COUNT(*) FROM student");
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public ArrayList<String> getStudentBatch(String sid) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT b.batch_no FROM batch b INNER JOIN student_batch sb ON sb.batch_no=b.batch_no WHERE sb.sid=?", sid);
        ArrayList<String> studentBatches = new ArrayList<>();
        while(rs.next()){
            studentBatches.add(rs.getString(1));
        }
        return studentBatches;
    }

}
