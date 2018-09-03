/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.ExamDAO;
import lk.ijse.sms.entity.Exam;

/**
 *
 * @author Sahan Rajakaruna
 */
public class ExamDAOImpl implements ExamDAO {

    @Override
    public boolean save(Exam entity) throws Exception {
        boolean executeUpdate = CrudUtil.executeUpdate("INSERT INTO exam (course_id,module_id) VALUES(?,?)",entity.getCourse_id(), entity.getModule_id());
       
        return executeUpdate;
    }

    @Override
    public boolean update(Exam entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE exam SET course_id = ?,module_id =? WHERE exam_id =?", entity.getCourse_id(), entity.getModule_id(), entity.getExam_id());
    }

    @Override
    public boolean delete(String id) throws Exception {

        return CrudUtil.executeUpdate("DELETE FROM exam WHERE exam_id = ?", id);
    }

    @Override
    public Exam findByID(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM exam WHERE exam_id = ?", id);
        if (rs.next()) {
            return new Exam(rs.getString(1), rs.getString(2), rs.getString(3));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Exam> getAll() throws Exception {
        ArrayList<Exam> allExams = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM exam");
        while (rs.next()) {
            allExams.add(new Exam(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return allExams;
    }

    @Override
    public boolean removeExam(String course_id, String module_code) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM exam WHERE course_id = ? AND module_id = ?",course_id,module_code);
    }

}
