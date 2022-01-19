package com.javacode.Service;

import com.javacode.DAO.RoleDao;
import com.javacode.Model.Role;
import com.javacode.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

 public    List<User> getAllRoles(){
       return roleDao.getAllRoles();
    }

    public  void add(Role role){
        roleDao.add(role);
    }

    public  void edit(Role role){
        roleDao.edit(role);
    }

    public Role getById(long id){
     return    roleDao.getById(id);
    }

    public Role getRoleByName(String roleName){
       return roleDao.getRoleByName(roleName);
    }
}
