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
public class CourseModule {

    /**
     * @return the courseModule_PK
     */
    public CourseModule_PK getCourseModule_PK() {
        return courseModule_PK;
    }

    /**
     * @param courseModule_PK the courseModule_PK to set
     */
    public void setCourseModule_PK(CourseModule_PK courseModule_PK) {
        this.courseModule_PK = courseModule_PK;
    }
    private CourseModule_PK courseModule_PK;

    public CourseModule() {
    }

    public CourseModule(CourseModule_PK courseModule_PK) {
        this.courseModule_PK = courseModule_PK;
    }
    public CourseModule(String course_id,String module_Id) {
        this.courseModule_PK = new CourseModule_PK(course_id, module_Id);
    }
    
}
