package com.javacode.DAO;

import com.javacode.Model.Role;
import com.javacode.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl  implements RoleDao{


    @PersistenceContext
  EntityManager entityManager;

    @Override
    public List<User> getAllRoles() {
        return entityManager.createQuery("  from   Role  ").getResultList();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);

    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);

    }

    @Override
    public Role getById(long id) {
        return entityManager.find(Role.class,id);
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT role FROM Role role WHERE role.name = :role",Role.class);
        return query
                .setParameter("role", name)
                //.setMaxResults(1)
                .getSingleResult();
    }
}
