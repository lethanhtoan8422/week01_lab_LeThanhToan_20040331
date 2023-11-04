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

public class AccountRepository {

    private final EntityManagerFactory entityManagerFactory;

    public AccountRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean create(Account account){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(account);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean update(Account account){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(account);
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
            Query query = entityManager.createQuery("UPDATE Account SET status = -1 WHERE id = :accountID");
            query.setParameter("accountID", id);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public Optional<Account> getByID(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Account account = null;

        try {
            transaction.begin();
            account = entityManager.find(Account.class, id);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return Optional.ofNullable(account);
    }

    public List<Account> getAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Account> accounts = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.status = 1");
            accounts = query.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return accounts;
    }

    public Optional<Account> checkAccount(String email, String password){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Account account = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email AND a.password = :password AND a.status = 1");
            query.setParameter("email", email);
            query.setParameter("password", password);
            account = (Account) query.getSingleResult();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return Optional.ofNullable(account);
    }

    public List<Account> getAccountsByRole(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Account> accounts = null;

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT a FROM GrantAccess g INNER JOIN Account a INNER JOIN Role r WHERE g.account.id = a.id AND g.role.id = r.id AND g.role.id = :roleID");
            query.setParameter("roleID", id);
            accounts = query.getResultList();
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return accounts;
    }
}
