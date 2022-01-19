package com.javacode.DAO;

import com.javacode.Model.Role;
import com.javacode.Model.User;

import java.util.List;
import java.util.Set;

public interface RoleDao {


    List<User> getAllRoles();

    void add(Role role);

    void edit(Role role);

    Role getById(long id);

    Role getRoleByName(String roleName);
}
