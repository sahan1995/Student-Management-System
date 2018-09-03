/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.StudentExamBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.StudentExamDAO;
import lk.ijse.sms.dto.StudentExamDTO;
import lk.ijse.sms.entity.StudentExam;
import lk.ijse.sms.entity.StudentExam_PK;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentExamBOImpl implements StudentExamBO {

    private StudentExamDAO studentExamDAO;

    public StudentExamBOImpl() {
        this.studentExamDAO = (StudentExamDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.student_exam);
    }

    @Override
    public boolean AddStudentExam(StudentExamDTO studentExam) throws Exception {

        return studentExamDAO.save(new StudentExam(studentExam.getId(), studentExam.getExam_id(), studentExam.getMarks()));

    }

    @Override
    public boolean UpdateStudentExam(StudentExamDTO studentExam) throws Exception {

        return studentExamDAO.update(new StudentExam(studentExam.getId(), studentExam.getExam_id(), studentExam.getMarks()));
    }

    @Override
    public boolean DeleteStudentExam(String sid, int exam_id) throws Exception {
        return studentExamDAO.delete(new StudentExam_PK(sid, exam_id));
    }

    @Override
    public StudentExamDTO FindbyID(String sid, int exam_id) throws Exception {
        StudentExam studentExamEntity = studentExamDAO.findByID(new StudentExam_PK(sid, exam_id));
        return new StudentExamDTO(studentExamEntity.getStudentExam_PK().getSidm(), studentExamEntity.getStudentExam_PK().getExamid(), studentExamEntity.getMarks());

    }

    @Override
    public ArrayList<StudentExamDTO> allStudentExamD() throws Exception {
        ArrayList<StudentExamDTO> allStudentExamDTO = new ArrayList<>();
        ArrayList<StudentExam> allStudentExamsEntity = studentExamDAO.getAll();
        for (StudentExam studentExam : allStudentExamsEntity) {
            allStudentExamDTO.add(new StudentExamDTO(studentExam.getStudentExam_PK().getSidm(), studentExam.getStudentExam_PK().getExamid(), studentExam.getMarks()));

        }
        return allStudentExamDTO;
    }

}
