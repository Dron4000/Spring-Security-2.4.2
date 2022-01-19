package com.javacode.Service;


import com.javacode.DAO.UserDAO;
import com.javacode.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// «Пользователь» – это просто Object. В большинстве случаев он может быть
//  приведен к классу UserDetails.
// Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;




    public UserDetailsServiceImpl() {

    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s)  {
        User userByName = userService.getUserByName(s);
        if (userByName==null){
            throw new UsernameNotFoundException("User not found " + s);
        }
        return userByName;
    }
}
