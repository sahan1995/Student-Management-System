/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import lk.ijse.sms.business.custom.StudentBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.StudentDAO;
import lk.ijse.sms.dto.StudentCourseDTO;
import lk.ijse.sms.dto.StudentDTO;
import lk.ijse.sms.entity.Student;
import lk.ijse.sms.entity.StudentCourse;

/**
 *
 * @author Sahan Rajakaruna
 */
public class StundetBOImpl implements StudentBO {

    private StudentDAO studentDAO;

    public StundetBOImpl() {
        this.studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.student);
    }

    @Override
    public boolean RegisterStudent(StudentDTO student) throws Exception {

        return studentDAO.save(new Student(student.getId(), student.getFname(), student.getLname(), student.getNic(), student.getContact(), student.getAddress()));

    }

    @Override
    public boolean UpdateStudent(StudentDTO student) throws Exception {

        return studentDAO.update(new Student(student.getId(), student.getFname(), student.getLname(), student.getNic(), student.getContact(), student.getAddress()));
    }

    @Override
    public boolean DeleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO findById(String id) throws Exception {
        Student studentEntity = studentDAO.findByID(id);
        if (studentEntity != null) {
            return new StudentDTO(studentEntity.getSid(), studentEntity.getFname(), studentEntity.getLname(), studentEntity.getNic(), studentEntity.getContact(), studentEntity.getAddress());
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<StudentDTO> allStudents() throws Exception {

        ArrayList<Student> allStudents = studentDAO.getAll();
        ArrayList<StudentDTO> allStudentDTO = new ArrayList<>();
        for (Student allStudentEntity : allStudents) {
            allStudentDTO.add(new StudentDTO(allStudentEntity.getSid(), allStudentEntity.getFname(), allStudentEntity.getLname(), allStudentEntity.getNic(), allStudentEntity.getContact(), allStudentEntity.getAddress()));

        }
        return allStudentDTO;
    }

    @Override
    public String generateCustomId() throws Exception {
        String lastID = studentDAO.getLastID();
        String[] part = lastID.split("STU");
        int no = Integer.parseInt(part[1]);
        no++;
        String customID = "STU" + formatNumber(no);
        System.out.println(customID);
        return customID;

    }

    private String formatNumber(long number) {
        NumberFormat nf = NumberFormat.getInstance();

        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumIntegerDigits(3);
        nf.setMinimumIntegerDigits(3);

        return nf.format(number);
    }

    @Override
    public ArrayList<StudentCourseDTO> studentCourse(String sid) throws Exception {
        ArrayList<StudentCourseDTO> studentCourseDTO = new ArrayList<>();
        ArrayList<StudentCourse> studentCourses = studentDAO.getStudentCourses(sid);
        for (StudentCourse studentCourse : studentCourses) {
            studentCourseDTO.add(new StudentCourseDTO(studentCourse.getCourse_id(), studentCourse.getBatch_no()));
        }
        return studentCourseDTO;
    }

    @Override
    public int getStudentCount() throws Exception {
        return studentDAO.studentCount();
    }

    @Override
    public ArrayList<String> getStudentBatch(String sid) throws Exception {
        return studentDAO.getStudentBatch(sid);
    }

}
