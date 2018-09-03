/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.QueryDAO;
import lk.ijse.sms.entity.CustomEntity;

/**
 *
 * @author Sahan Rajakaruna
 */
public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> batchExam(String batch_no) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT DISTINCT m.module_id AS Module_code, m.modulename AS Exam_Module,C.course_id,e.exam_id FROM module m INNER JOIN exam e ON e.module_id= m.module_id INNER JOIN course c ON e.course_id=c.course_id INNER JOIN batch b on c.course_id = b.course_id INNER JOIN student_batch sb ON sb.batch_no = b.batch_no where C.course_id=?", batch_no);
        
        ArrayList<CustomEntity> alExams = new ArrayList<>();
        while (rs.next()) {

            CustomEntity customEntity = new CustomEntity(rs.getInt(4), rs.getString(2));
            alExams.add(customEntity);
//            return customEntity;
        }
        return alExams;
    }

}
