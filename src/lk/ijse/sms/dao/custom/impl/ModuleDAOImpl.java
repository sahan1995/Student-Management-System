/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.dao.custom.impl;

import com.mysql.jdbc.log.NullLogger;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.sms.dao.CrudUtil;
import lk.ijse.sms.dao.custom.ModuleDAO;
import lk.ijse.sms.entity.Module;

/**
 *
 * @author Sahan Rajakaruna
 */
public class ModuleDAOImpl implements ModuleDAO {

    @Override
    public boolean save(Module entity) throws Exception {

        return CrudUtil.executeUpdate("INSERT INTO module VALUES (?,?)", entity.getModule_id(), entity.getModule_name());
    }

    @Override
    public boolean update(Module entity) throws Exception {

        return CrudUtil.executeUpdate("UPDATE module SET modulename = ? WHERE module_id = ?", entity.getModule_name(), entity.getModule_id());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM module WHERE module_id =?", id);
    }

    @Override
    public Module findByID(String id) throws Exception {

        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM module WHERE module_id =? ", id);
        if (rs.next()) {
            return new Module(rs.getString(1), rs.getString(2));
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<Module> getAll() throws Exception {

        ArrayList<Module> allModules = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT * FROM module");
        while (rs.next()) {
            allModules.add(new Module(rs.getString(1), rs.getString(2)));
        }
        return allModules;
    }

    @Override
    public String getLastID() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT module_id FROM module ORDER BY module_id DESC LIMIT 1");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<String> getModuleId(String... args) throws Exception {
        ArrayList<String> getId = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {

            ResultSet rs = CrudUtil.executeQuery("SELECT module_id FROM module WHERE modulename = ?", args[i]);
            if (rs.next()) {
                getId.add(rs.getString(1));
            }
        }
        return getId;
    }

    @Override
    public ArrayList<String> getModules(String course_id) throws Exception {
       ArrayList<String> courseModules  = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("SELECT m.modulename from module m INNER JOIN course_module cm ON m.module_id =cm.module_id WHERE cm.course_id=?", course_id);
        while(rs.next()){
            courseModules.add(rs.getString(1));
        }
        return courseModules;
    }

    @Override
    public String getModuleId(String moduleName) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("SELECT module_id FROM module WHERE modulename = ?", moduleName);
        if(rs.next()){
            return rs.getString(1);
        }else{
            return null;
        }
    }

    @Override
    public int moduleCount() throws Exception {
         ResultSet rs = CrudUtil.executeQuery("SELECT COUNT(*) FROM module");
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

}
