package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.models.Role;

import java.util.List;

public class GrantAccessRepository {
    private final EntityManagerFactory entityManagerFactory;

    public GrantAccessRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean create(GrantAccess grantAccess){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(grantAccess);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean update(GrantAccess grantAccess){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(grantAccess);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean delete(long accountId, long roleId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE GrantAccess SET isGrant = 0 WHERE account.id = :accountID AND role.id = :roleID");
            query.setParameter("accountID", accountId);
            query.setParameter("roleID", roleId);
            GrantAccess grantAccess = (GrantAccess) query.getSingleResult();
            entityManager.remove(grantAccess);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public GrantAccess getByID(long accountId, long roleId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        GrantAccess grantAccess = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT g FROM GrantAccess g WHERE g.account.id = :accountID AND g.role.id = :roleID");
            query.setParameter("accountID", accountId);
            query.setParameter("roleID", roleId);
            grantAccess = (GrantAccess) query.getSingleResult();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return grantAccess;
    }

    public List<GrantAccess> getAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<GrantAccess> grantAccesses = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT g FROM GrantAccess g WHERE g.isGrant = 1");
            grantAccesses = query.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return grantAccesses;
    }
}
