package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Users;

public class UserDB {

    public void insert(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally {
            em.close();
        }
        
    }

    public void update(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public List<Users> getAll(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            //List<Users> user = em.createNamedQuery("Users.findAll", Users.class).getResultList();
            //return user;
            
            Users user = em.find(Users.class, username);
            return (List<Users>) user;
        } finally {
            em.close();
        }
        
    }

    public Users getUser(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Users user = em.find(Users.class,username);
            return user;
        }finally {
            em.close();
        }
    }

    public void delete(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally {
            em.close();
        }
    }
}
