/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.ExamBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.ExamDAO;
import lk.ijse.sms.dto.ExamDTO;
import lk.ijse.sms.entity.Exam;

/**
 *
 * @author Sahan Rajakaruna
 */
public class ExamBOImpl implements ExamBO {

    private ExamDAO examDAO;

    public ExamBOImpl() {
        this.examDAO = (ExamDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.exam);
    }

    @Override
    public boolean AddExam(ExamDTO exam) throws Exception {
        System.out.println(exam);
        return examDAO.save(new Exam(exam.getExam_id(), exam.getCourse_id(), exam.getModule_id()));
    }

    @Override
    public boolean UpdateExam(ExamDTO exam) throws Exception {

        return examDAO.update(new Exam(exam.getExam_id(), exam.getCourse_id(), exam.getModule_id()));
    }

    @Override
    public boolean DeleteExam(String id) throws Exception {

        return examDAO.delete(id);

    }

    @Override
    public ExamDTO findById(String id) throws Exception {
        Exam examEnity = examDAO.findByID(id);
        return new ExamDTO(examEnity.getExam_id(), examEnity.getCourse_id(), examEnity.getModule_id());
    }

    @Override
    public ArrayList<ExamDTO> allExam() throws Exception {

        ArrayList<ExamDTO> examDTO = new ArrayList<>();
        ArrayList<Exam> allexamEntity = examDAO.getAll();
        for (Exam exam : allexamEntity) {
            examDTO.add(new ExamDTO(exam.getExam_id(), exam.getCourse_id(), exam.getModule_id()));
        }
        return examDTO;
    }

    @Override
    public boolean RemoveExam(String course_id, String module_code) throws Exception {

        return examDAO.removeExam(course_id, module_code);
    }

}
