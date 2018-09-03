/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import lk.ijse.sms.business.custom.CourseBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.CourseDAO;
import lk.ijse.sms.dto.CourseDTO;
import lk.ijse.sms.entity.Course;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseBOImpl implements CourseBO {

    private CourseDAO courseDAO;

    public CourseBOImpl() {
        this.courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.course);
    }

    @Override
    public boolean RegisterCourse(CourseDTO course) throws Exception {

        return courseDAO.save(new Course(course.getCourse_id(), course.getCourse_title(), course.getDuration()));

    }

    @Override
    public boolean UpdateCourse(CourseDTO course) throws Exception {
        return courseDAO.update(new Course(course.getCourse_id(), course.getCourse_title(), course.getDuration()));
    }

    @Override
    public boolean DeleteCourse(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDTO findById(String id) throws Exception {

        Course courseEntity = courseDAO.findByID(id);
        if(courseEntity!=null){
             return new CourseDTO(courseEntity.getCourse_id(), courseEntity.getCourse_title(), courseEntity.getDuration());
        }else{
            return null;
        }
       

    }

    @Override
    public ArrayList<CourseDTO> allCourses() throws Exception {

        ArrayList<CourseDTO> allCourseDTO = new ArrayList<>();
        ArrayList<Course> courseEntity = courseDAO.getAll();
        for (Course course : courseEntity) {

            allCourseDTO.add(new CourseDTO(course.getCourse_id(), course.getCourse_title(), course.getDuration()));

        }
        return allCourseDTO;
    }

    @Override
    public String findIdByTitle(String courseTitle) throws Exception {

        return courseDAO.getIDByTitle(courseTitle);
    }

    @Override
    public String generateCoutomID() throws Exception {

        String id = courseDAO.getLastID();
        String[] part = id.split("C");
        int no = Integer.parseInt(part[1]);
        no++;
        String customID = "C" + formatNumber(no);
        System.out.println(customID);
        return customID;

    }
    
    private String formatNumber(long number){
        NumberFormat nf = NumberFormat.getInstance();
        
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumIntegerDigits(3);
        nf.setMinimumIntegerDigits(3);
        
        return nf.format(number);
    }

    @Override
    public int courseCount() throws Exception {
       return courseDAO.CourseCount();
    }

    @Override
    public String getCoursrId(String batch_no) throws Exception {
        
        return courseDAO.courseId(batch_no);        
    }

}
