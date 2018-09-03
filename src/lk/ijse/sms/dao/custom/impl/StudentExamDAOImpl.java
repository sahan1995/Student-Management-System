/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.StudentExamDAO;
import lk.ijse.sms.entity.StudentExam;
import lk.ijse.sms.entity.StudentExam_PK;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentExamDAOImpl implements StudentExamDAO {

    @Override
    public boolean save(StudentExam entity) throws Exception {

        return CrudUtil.executeUpdate("INSERT INTO student_exam VALUES(?,?,?)", entity.getStudentExam_PK().getSidm(), entity.getStudentExam_PK().getExamid(), entity.getMarks());

    }

    @Override
    public boolean update(StudentExam entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE student_exam SET marks = ? WHERE sid =? AND exam_id = ?", entity.getMarks(), entity.getStudentExam_PK().getSidm(), entity.getStudentExam_PK().getExamid());
    }

    @Override
    public boolean delete(StudentExam_PK id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM student_exam WHERE  sid =? AND exam_id = ?", id.getSidm(), id.getExamid());
    }

    @Override
    public StudentExam findByID(StudentExam_PK id) throws Exception {

        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM student_exam WHERE  sid =? AND exam_id = ?", id.getSidm(), id.getExamid());
        if (rs.next()) {
            return new StudentExam(rs.getString(1), rs.getInt(2), rs.getBigDecimal(3));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<StudentExam> getAll() throws Exception {
        ArrayList<StudentExam> allStudentExams = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM student_exam");
        while (rs.next()) {
            allStudentExams.add(new StudentExam(rs.getString(1), rs.getInt(2), rs.getBigDecimal(3)));
        }
        return allStudentExams;
    }

}
