/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.entity;

/**
 *
 * @author Sahan Rajakaruna
 */
public class Exam {
    
    /**
     * @return the exam_id
     */
    public String getExam_id() {
        return exam_id;
    }

    /**
     * @param exam_id the exam_id to set
     */
    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    /**
     * @return the course_id
     */
    public String getCourse_id() {
        return course_id;
    }

    /**
     * @param course_id the course_id to set
     */
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    /**
     * @return the module_id
     */
    public String getModule_id() {
        return module_id;
    }

    /**
     * @param module_id the module_id to set
     */
    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }
    private String exam_id;
    private String course_id;
    private String module_id;

    public Exam() {
    }

    public Exam(String exam_id, String course_id, String module_id) {
        this.exam_id = exam_id;
        this.course_id = course_id;
        this.module_id = module_id;
    }

    @Override
    public String toString() {
        return "Exam{" + "exam_id=" + exam_id + ", course_id=" + course_id + ", module_id=" + module_id + '}';
    }
    
}
