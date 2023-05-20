package model.estatisticas_jogador;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EstatisticasJogadorMapper implements Mapper<EstatisticasJogador, Integer> {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void Create(EstatisticasJogador entity) throws Exception {
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
    public EstatisticasJogador Read(Integer id) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            return em.find(EstatisticasJogador.class, id);
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
    public void Update(EstatisticasJogador entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            EstatisticasJogador ej = em.find(EstatisticasJogador.class, entity.getJogador(), LockModeType.PESSIMISTIC_WRITE );
            if (ej == null)
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
    public void Delete(EstatisticasJogador entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            EstatisticasJogador ej = em.find(EstatisticasJogador.class, entity.getJogador(), LockModeType.PESSIMISTIC_WRITE );
            if (ej == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(ej);
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


