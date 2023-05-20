package model.jogo;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JogoMapper implements Mapper<Jogo, Integer> {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void Create(Jogo entity) throws Exception {
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public Jogo Read(Integer id) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            return em.find(Jogo.class, id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public void Update(Jogo entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Jogo j = em.find(Jogo.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (j == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.merge(entity);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public void Delete(Jogo entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Jogo j = em.find(Jogo.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (j == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(j);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            em.close();
            emf.close();
        }
    }
}