package com.javacode.Service;

import com.javacode.Model.User;
import com.javacode.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);

    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);

    }

    @Override
    public void edit(User user) {
        userDAO.edit(user);

    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) {
        User userByName = getUserByName(s);
        if (userByName == null) {
            throw new UsernameNotFoundException("User not found " + s);
        }
        return userByName;
    }
}
