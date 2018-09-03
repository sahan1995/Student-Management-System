/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.entity;

import java.math.BigDecimal;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StudentExam {

    /**
     * @return the studentExam_PK
     */
    public StudentExam_PK getStudentExam_PK() {
        return studentExam_PK;
    }

    /**
     * @param studentExam_PK the studentExam_PK to set
     */
    public void setStudentExam_PK(StudentExam_PK studentExam_PK) {
        this.studentExam_PK = studentExam_PK;
    }

    /**
     * @return the marks
     */
    public BigDecimal getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(BigDecimal marks) {
        this.marks = marks;
    }
    private StudentExam_PK studentExam_PK;
    private BigDecimal marks;

    public StudentExam() {
    }

    public StudentExam(StudentExam_PK studentExam_PK, BigDecimal marks) {
        this.studentExam_PK = studentExam_PK;
        this.marks = marks;
    }
    public StudentExam(String sid,int exam_id, BigDecimal marks) {
        this.studentExam_PK = new StudentExam_PK(sid, exam_id);
        this.marks = marks;
    }
    
}
