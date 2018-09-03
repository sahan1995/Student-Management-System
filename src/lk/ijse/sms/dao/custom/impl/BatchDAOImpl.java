/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.BatchDAO;
import lk.ijse.sms.entity.Batch;
import lk.ijse.sms.entity.BatchExam;

/**
 *
 * @author Sahan Rajakaruna
 */
public class BatchDAOImpl implements BatchDAO {

    @Override
    public boolean save(Batch entity) throws Exception {

        return CrudUtil.executeUpdate("INSERT INTO batch VALUES (?,?,?)", entity.getBatch_no(), entity.getFee(), entity.getCourse_id());

    }

    @Override
    public boolean update(Batch entity) throws Exception {

        return CrudUtil.executeUpdate("UPDATE batch SET fee = ?,course_id =? WHERE batch_no = ?", entity.getFee(), entity.getCourse_id(), entity.getBatch_no());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM batch WHERE batch_no = ?", id);
    }

    @Override
    public Batch findByID(String id) throws Exception {

        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM batch WHERE batch_no = ?", id);
        if (rs.next()) {
            return new Batch(rs.getString(1), rs.getBigDecimal(2), rs.getString(3));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Batch> getAll() throws Exception {

        ArrayList<Batch> getBatchs = new ArrayList<>();

        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM batch");
        while (rs.next()) {
            getBatchs.add(new Batch(rs.getString(1), rs.getBigDecimal(2), rs.getString(3)));

        }
        return getBatchs;
    }

    @Override
    public ArrayList<String> getBatchesByCourse(String course_id) throws Exception {
        ArrayList<String> Batches = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT batch_no FROM batch WHERE course_id = ?", course_id);
        while (rs.next()) {
            Batches.add(rs.getString(1));
        }
        return Batches;
    }

    @Override
    public ArrayList<String> batchStudents(String batch_no) throws Exception {
        ArrayList<String> batchStudents = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT s.fname,s.lname, s.sid FROM student s INNER JOIN student_batch sb ON sb.sid=s.sid WHERE sb.batch_no=?", batch_no);

        while (rs.next()) {

            batchStudents.add(rs.getString(3) + " -  " + rs.getString(1) + " " + rs.getString(2));
        }

        return batchStudents;
    }

    @Override
    public ArrayList<BatchExam> batchExams(String batch_no) throws Exception {

        ResultSet rs = CrudUtil.executeQuery("SELECT DISTINCT m.module_id AS Module_code, m.modulename AS Exam_Module,C.course_id,e.exam_id FROM module m INNER JOIN exam e ON e.module_id= m.module_id INNER JOIN course c ON e.course_id=c.course_id INNER JOIN batch b on c.course_id = b.course_id INNER JOIN student_batch sb ON sb.batch_no = b.batch_no where C.course_id=?", batch_no);
        ArrayList<BatchExam> batchExams = new ArrayList<>();

        while (rs.next()) {
            batchExams.add(new BatchExam(rs.getInt(4), rs.getString(2)));
        }

        return batchExams;
    }

    @Override
    public String getLastID() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT batch_no FROM batch ORDER BY batch_no DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public int BatchCount() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT COUNT(*) FROM batch");
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public ArrayList<String> allBatches() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select batch_no from batch ");
        ArrayList<String> batches = new ArrayList<>();
        while (rs.next()) {
            batches.add(rs.getString(1));
        }
        return batches;
    }

}
