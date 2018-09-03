/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.sql.Connection;
import lk.ijse.sms.business.custom.RegistrationBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.StudentBatchDAO;
import lk.ijse.sms.dao.custom.StudentDAO;
import lk.ijse.sms.db.DbConnection;
import lk.ijse.sms.dto.StudentBatchDTO;
import lk.ijse.sms.dto.StudentDTO;
import lk.ijse.sms.entity.Student;
import lk.ijse.sms.entity.StudentBatch;

/**
 *
 * @author Sahan Rajakaruna
 */
public class RegistrationBOImpl implements RegistrationBO {

    private StudentDAO studentDAO;
    private StudentBatchDAO student_batchDAO;

    public RegistrationBOImpl() {
        this.student_batchDAO = (StudentBatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.student_batch);
        this.studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.student);
    }

    @Override
    public boolean RegisterNewStudent(StudentDTO student, StudentBatchDTO studentBatch) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);
        try {

            boolean result1 = studentDAO.save(new Student(student.getId(), student.getFname(), student.getLname(), student.getNic(), student.getContact(), student.getAddress()));
            if (result1 == true) {

                boolean result2 = student_batchDAO.save(new StudentBatch(studentBatch.getId(), studentBatch.getBatch_bo()));
                if (result2 == true) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

    }

    @Override
    public boolean RegisterToBatch(StudentBatchDTO studentBatch) throws Exception {
        return student_batchDAO.save(new StudentBatch(studentBatch.getId(), studentBatch.getBatch_bo()));
    }

}
