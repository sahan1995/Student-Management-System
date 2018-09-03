/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.CourseModuleBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.CourseModuleDAO;
import lk.ijse.sms.dto.CourseModuleDTO;
import lk.ijse.sms.entity.CourseModule;
import lk.ijse.sms.entity.CourseModule_PK;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseModuleBOImpl implements CourseModuleBO {

    private CourseModuleDAO courseModuleDAO;

    public CourseModuleBOImpl() {
        this.courseModuleDAO = (CourseModuleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.course_module);
    }

    @Override
    public boolean AddCourseModule(CourseModuleDTO courseModule) throws Exception {

        return courseModuleDAO.save(new CourseModule(courseModule.getCourse_id(), courseModule.getModule_id()));

    }

    @Override
    public boolean UpdateCourseModule(CourseModuleDTO courseModule) throws Exception {

        return courseModuleDAO.update(new CourseModule(courseModule.getCourse_id(), courseModule.getModule_id()));
    }

    @Override
    public boolean DeleteCourseModule(String course_id, String module_id) throws Exception {

        return courseModuleDAO.delete(new CourseModule_PK(course_id, module_id));
    }

    @Override
    public CourseModuleDTO findById(String course_id, String module_id) throws Exception {
        CourseModule courseModuleEntity = courseModuleDAO.findByID(new CourseModule_PK(course_id, module_id));
        return new CourseModuleDTO(courseModuleEntity.getCourseModule_PK().getCourse_id(), courseModuleEntity.getCourseModule_PK().getModule_id());
    }

    @Override
    public ArrayList<CourseModuleDTO> allCourseModules() throws Exception {
        ArrayList<CourseModuleDTO> allCourseModuleDTO = new ArrayList<>();
        ArrayList<CourseModule> allCourseModulesEntity = courseModuleDAO.getAll();
        for (CourseModule courseModule : allCourseModulesEntity) {
            allCourseModuleDTO.add(new CourseModuleDTO(courseModule.getCourseModule_PK().getCourse_id(), courseModule.getCourseModule_PK().getModule_id()));
        }
        return allCourseModuleDTO;

    }

    @Override
    public boolean addCourseModule(String courseid, String... moduleId) throws Exception {
        return courseModuleDAO.addCourseModule(courseid, moduleId);
    }

}
