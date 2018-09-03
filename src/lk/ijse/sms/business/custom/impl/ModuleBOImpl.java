/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.ModuleBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.ModuleDAO;
import lk.ijse.sms.dto.ModuleDTO;
import lk.ijse.sms.entity.Module;

/**
 *
 * @author Sahan Rajakaruna
 */
public class ModuleBOImpl implements ModuleBO {

    private ModuleDAO moduleDAO;

    public ModuleBOImpl() {
        this.moduleDAO = (ModuleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.module);
    }

    @Override
    public boolean AddModule(ModuleDTO module) throws Exception {

        return moduleDAO.save(new Module(module.getModule_id(), module.getModule_name()));
    }

    @Override
    public boolean UpdateModule(ModuleDTO module) throws Exception {

        return moduleDAO.update(new Module(module.getModule_id(), module.getModule_name()));
    }

    @Override
    public boolean DeleteModule(String id) throws Exception {
        return moduleDAO.delete(id);
    }

    @Override
    public ModuleDTO findById(String id) throws Exception {
        Module moduleEntity = moduleDAO.findByID(id);
        if (moduleEntity != null) {
            return new ModuleDTO(moduleEntity.getModule_id(), moduleEntity.getModule_name());
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<ModuleDTO> allModules() throws Exception {
        ArrayList<ModuleDTO> allModuleDTO = new ArrayList<>();
        ArrayList<Module> allModuleEntity = moduleDAO.getAll();
        for (Module module : allModuleEntity) {
            allModuleDTO.add(new ModuleDTO(module.getModule_id(), module.getModule_name()));

        }
        return allModuleDTO;
    }

    @Override
    public String generateCustomId() throws Exception {
        String lastID = moduleDAO.getLastID();

        String[] part = lastID.split("MOD");
        int no = Integer.parseInt(part[1]);
        no++;
        String customID = "MOD" + no;
        return customID;
    }

    @Override
    public ArrayList<String> getID(String... args) throws Exception {
        return moduleDAO.getModuleId(args);
    }

    @Override
    public ArrayList<String> getCourseModules(String course_id) throws Exception {
        return moduleDAO.getModules(course_id);
    }

    @Override
    public String getModuleID(String course_title) throws Exception {
        return moduleDAO.getModuleId(course_title);
    }

    @Override
    public int moduleCount() throws Exception {
        return moduleDAO.moduleCount();
    }

}
