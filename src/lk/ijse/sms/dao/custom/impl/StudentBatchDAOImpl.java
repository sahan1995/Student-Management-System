/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.StudentBatchDAO;
import lk.ijse.sms.entity.StudentBatch;
import lk.ijse.sms.entity.StudentBatch_PK;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentBatchDAOImpl implements StudentBatchDAO {

    @Override
    public boolean save(StudentBatch entity) throws Exception {

        return CrudUtil.executeUpdate("INSERT INTO student_batch VALUES(?,?)", entity.getStudentBatch_PK().getSid(), entity.getStudentBatch_PK().getBatch_no());

    }

    @Override
    public boolean update(StudentBatch entity) throws Exception {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(StudentBatch_PK id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM student_batch WHERE sid = ? batch_no = ?", id.getSid(), id.getBatch_no());
    }

    @Override
    public StudentBatch findByID(StudentBatch_PK id) throws Exception {

        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM student_batch WHERE sid = ? batch_no = ?", id.getSid(), id.getBatch_no());
        if (rs.next()) {
            return new StudentBatch(rs.getString(1), rs.getString(2));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<StudentBatch> getAll() throws Exception {
        ArrayList<StudentBatch> allStudentBatchs = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM student_batch");
        while (rs.next()) {
            allStudentBatchs.add(new StudentBatch(rs.getString(1), rs.getString(2)));
        }
        return allStudentBatchs;
    }

}
