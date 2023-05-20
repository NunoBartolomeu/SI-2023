package model.mensagen;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MensagemMapper implements Mapper<Mensagem, MensagemId> {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void Create(Mensagem entity) throws Exception {
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
    public Mensagem Read(MensagemId id) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            return em.find(Mensagem.class, id);
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
    public void Update(Mensagem entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Mensagem c = em.find(Mensagem.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (c == null)
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
    public void Delete(Mensagem entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Mensagem c = em.find(Mensagem.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (c == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(c);
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