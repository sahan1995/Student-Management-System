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
public class StudentBatch {

    /**
     * @return the studentBatch_PK
     */
    public StudentBatch_PK getStudentBatch_PK() {
        return studentBatch_PK;
    }

    /**
     * @param studentBatch_PK the studentBatch_PK to set
     */
    public void setStudentBatch_PK(StudentBatch_PK studentBatch_PK) {
        this.studentBatch_PK = studentBatch_PK;
    }

    private StudentBatch_PK studentBatch_PK;
    
    public StudentBatch() {
    }
    
    public StudentBatch(StudentBatch_PK studentBatch_PK) {
        this.studentBatch_PK = studentBatch_PK;
    }
    
    public StudentBatch(String sid, String batch_no) {
        this.studentBatch_PK = new StudentBatch_PK(sid, batch_no);
    }

    @Override
    public String toString() {
        return "StudentBatch{" + "studentBatch_PK=" + getStudentBatch_PK() + '}';
    }
   
    
}
