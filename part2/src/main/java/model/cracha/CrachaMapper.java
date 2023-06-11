package model.cracha;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.DataScope;
import model.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CrachaMapper implements Mapper<Cracha, CrachaId> {
    @Override
    public void Create(Cracha entity) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em  = ds.getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public Cracha Read(CrachaId id) throws Exception{
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Cracha cracha = em.find(Cracha.class, id);

            ds.validateWork();
            return cracha;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void Update(Cracha entity) throws Exception{
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Cracha c = em.find(Cracha.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (c == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.merge(entity);

            ds.validateWork();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void Delete(Cracha entity) throws Exception{
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            em.getTransaction().begin();
            Cracha c = em.find(Cracha.class, entity, LockModeType.PESSIMISTIC_WRITE );
            if (c == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(c);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
