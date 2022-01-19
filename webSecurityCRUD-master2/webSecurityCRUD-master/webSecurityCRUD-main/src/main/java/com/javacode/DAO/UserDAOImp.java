package com.javacode.DAO;

import com.javacode.Model.Role;
import com.javacode.Model.User;
import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class UserDAOImp implements UserDAO {


    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select  u from User  u ",User.class).getResultList();
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }



    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));

    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String userName) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT user FROM User user WHERE user.username =:username", User.class);
        return query
                .setParameter("username", userName)
                .setMaxResults(1)
                .getSingleResult();
    }
}
