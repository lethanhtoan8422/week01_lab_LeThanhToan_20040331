package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.Role;

import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private final EntityManagerFactory entityManagerFactory;

    public RoleRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean create(Role role){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(role);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean update(Role role){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(role);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean delete(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE Role SET status = -1 WHERE id = :roleID");
            query.setParameter("roleID", id);
            int rs = query.executeUpdate();
            transaction.commit();
            return rs > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public Optional<Role> getByID(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Role role = null;

        try {
            transaction.begin();
            role = entityManager.find(Role.class, id);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return Optional.ofNullable(role);
    }

    public List<Role> getAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Role> roles = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.status = 1");
            roles = query.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return roles;
    }

    public List<Role> getRolesByAccount(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Role> roles = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT r FROM GrantAccess g INNER JOIN Account a INNER JOIN Role r WHERE g.account.id = a.id AND g.role.id = r.id AND g.account.id = :accountID");
            query.setParameter("accountID", id);
            roles = query.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return roles;
    }
}
