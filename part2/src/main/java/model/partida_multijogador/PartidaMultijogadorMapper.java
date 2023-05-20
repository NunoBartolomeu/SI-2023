package model.partida_multijogador;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PartidaMultijogadorMapper implements Mapper<PartidaMultijogador, PartidaId> {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void Create(PartidaMultijogador entity) throws Exception {
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
    public PartidaMultijogador Read(PartidaMultijogadorId id) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            return em.find(PartidaMultijogador.class, id);
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
    public void Update(PartidaMultijogador entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            PartidaMultijogador c = em.find(PartidaMultijogador.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
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
    public void Delete(PartidaMultijogadorId id) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            PartidaMultijogador c = em.find(PartidaMultijogador.class, id, LockModeType.PESSIMISTIC_WRITE );
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