package model.pontuacão;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.Mapper;
import model.regiao.Regiao;
/*
public class PontuacaoMapper implements Mapper<Pontuacao_Multi_Jogador, String> {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void Create(Pontuacao_Multi_Jogador entity) throws Exception {
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
    public Pontuacao_Multi_Jogador Read(String id) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            return em.find(Pontuacao_Multi_Jogador.class, id);
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
    public void Update(Pontuacao_Multi_Jogador entity) throws Exception{
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pontuacao_Multi_Jogador r = em.find(Regiao.class, entity.getNome(), LockModeType.PESSIMISTIC_WRITE );
            if (r == null)
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
    public void Delete(Regiao entity) throws Exception {
        emf = Persistence.createEntityManagerFactory("JPA_SI");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(entity);
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

 */
