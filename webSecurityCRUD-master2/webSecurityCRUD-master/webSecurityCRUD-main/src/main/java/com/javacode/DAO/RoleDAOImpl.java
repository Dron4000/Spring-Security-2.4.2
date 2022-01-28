package com.javacode.DAO;

import com.javacode.Model.Role;
import com.javacode.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDao {


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
        return entityManager.find(Role.class, id);
    }


    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT role FROM Role role  WHERE role.name = :role", Role.class);
        return query
                .setParameter("role", name)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public Set<Role> getSetRoles(Set<String> roles) {
        return new HashSet<>(entityManager.createQuery("select role from Role role where role.name = :role", Role.class)
                .setParameter("role", roles)
                .getResultList());
    }


}
