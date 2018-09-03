/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.StudentBatchBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.StudentBatchDAO;
import lk.ijse.sms.dto.StudentBatchDTO;
import lk.ijse.sms.entity.StudentBatch;
import lk.ijse.sms.entity.StudentBatch_PK;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentBatchBOImpl implements StudentBatchBO {

    private StudentBatchDAO studentBatchDAO;

    public StudentBatchBOImpl() {
        this.studentBatchDAO = (StudentBatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.student_batch);
    }

    @Override
    public boolean AddStudentBatch(StudentBatchDTO studentBatch) throws Exception {
        return studentBatchDAO.save(new StudentBatch(studentBatch.getId(), studentBatch.getBatch_bo()));

    }

    @Override
    public boolean UpdateStudentBatch(StudentBatchDTO studentBatch) throws Exception {

        return studentBatchDAO.update(new StudentBatch(studentBatch.getId(), studentBatch.getBatch_bo()));
    }

    @Override
    public boolean DeleteStudentBatch(String sid, String batch_id) throws Exception {

        return studentBatchDAO.delete(new StudentBatch_PK(sid, batch_id));
    }

    @Override
    public StudentBatchDTO findById(String sid, String batch_id) throws Exception {

        StudentBatch studentBatchEntity = studentBatchDAO.findByID(new StudentBatch_PK(sid, batch_id));
        return new StudentBatchDTO(studentBatchEntity.getStudentBatch_PK().getSid(), studentBatchEntity.getStudentBatch_PK().getBatch_no());
    }

    @Override
    public ArrayList<StudentBatchDTO> allStudentBatchs() throws Exception {
        ArrayList<StudentBatch> allStudentBatchEntity = studentBatchDAO.getAll();
        ArrayList<StudentBatchDTO> allStudentBatchDTO = new ArrayList<>();
        for (StudentBatch studentBatch : allStudentBatchEntity) {

            allStudentBatchDTO.add(new StudentBatchDTO(studentBatch.getStudentBatch_PK().getSid(), studentBatch.getStudentBatch_PK().getBatch_no()));
        }
        return allStudentBatchDTO;

    }

}
