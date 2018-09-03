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
public class StudentBatch_PK {
    
    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the batch_no
     */
    public String getBatch_no() {
        return batch_no;
    }

    /**
     * @param batch_no the batch_no to set
     */
    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }
    private String sid;
    private String batch_no;

    public StudentBatch_PK() {
    }

    public StudentBatch_PK(String sid, String batch_no) {
        this.sid = sid;
        this.batch_no = batch_no;
    }
    
}
