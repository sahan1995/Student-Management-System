/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import lk.ijse.sms.business.custom.BatchBO;
import lk.ijse.sms.dao.CrudDAO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.QueryDAO;
import lk.ijse.sms.dao.custom.BatchDAO;
import lk.ijse.sms.dto.BatchDTO;
import lk.ijse.sms.dto.BatchExamDTO;
import lk.ijse.sms.dto.CustomDTO;
import lk.ijse.sms.entity.Batch;
import lk.ijse.sms.entity.BatchExam;
import lk.ijse.sms.entity.CustomEntity;

/**
 *
 * @author Sahan Rajakaruna
 */
public class BatchBOImpl implements BatchBO {

    private BatchDAO batchDAO;
    private QueryDAO queryDAO;

    public BatchBOImpl() {
        this.queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.querydao);
        this.batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.batch);
    }

    @Override
    public boolean RegisterBatch(BatchDTO batch) throws Exception {

        return batchDAO.save(new Batch(batch.getBatch_no(), batch.getFee(), batch.getCourse_id()));
    }

    @Override
    public boolean UpdateBatch(BatchDTO batch) throws Exception {

        return batchDAO.update(new Batch(batch.getBatch_no(), batch.getFee(), batch.getCourse_id()));
    }

    @Override
    public boolean DeleteBatch(String id) throws Exception {
        return batchDAO.delete(id);
    }

    @Override
    public BatchDTO findById(String id) throws Exception {
        Batch BatchEntity = batchDAO.findByID(id);
        if (BatchEntity != null) {
            return new BatchDTO(BatchEntity.getBatch_no(), BatchEntity.getFee(), BatchEntity.getCourse_id());
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<BatchDTO> allBatches() throws Exception {
        ArrayList<BatchDTO> AllBatchDTO = new ArrayList<>();
        ArrayList<Batch> allBatchEntity = batchDAO.getAll();
        for (Batch batch : allBatchEntity) {

            AllBatchDTO.add(new BatchDTO(batch.getBatch_no(), batch.getFee(), batch.getCourse_id()));
        }
        return AllBatchDTO;
    }

    @Override
    public ArrayList<String> findAllBachesByCourse(String courseID) throws Exception {
        return batchDAO.getBatchesByCourse(courseID);
    }

    @Override
    public ArrayList<String> batchStudent(String batch_no) throws Exception {
        return batchDAO.batchStudents(batch_no);
    }

    @Override
    public ArrayList<BatchExamDTO> batchExams(String batch_no) throws Exception {
        ArrayList<BatchExam> batchExamsEntity = batchDAO.batchExams(batch_no);
        ArrayList<BatchExamDTO> batchExam = new ArrayList<>();
        for (BatchExam batchExamEn : batchExamsEntity) {
            batchExam.add(new BatchExamDTO(batchExamEn.getExam_id(), batchExamEn.getExam_module()));
        }
        return batchExam;

    }

    @Override
    public String getLastID() throws Exception {
        return batchDAO.getLastID();
    }

    @Override
    public int batchCount() throws Exception {
        return batchDAO.BatchCount();
    }

    @Override
    public ArrayList<String> allBatchs() throws Exception {
        return batchDAO.allBatches();
    }

    @Override
    public ArrayList<CustomDTO> batchExam(String course_id) throws Exception {
        ArrayList<CustomEntity> batchExam = queryDAO.batchExam(course_id);
        ArrayList<CustomDTO> exam = new ArrayList<>();
        for (CustomEntity customEntity : batchExam) {
            exam.add(new CustomDTO(customEntity.getExam_id(), customEntity.getModule_name()));
        }
        return exam;
    }

}
