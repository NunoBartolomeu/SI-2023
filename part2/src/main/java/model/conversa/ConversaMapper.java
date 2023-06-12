package model.conversa;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.DataScope;
import model.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ConversaMapper implements Mapper<Conversa, Integer> {
    @Override
    public void Create(Conversa entity) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.persist(entity);
            ds.validateWork();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public Conversa Read(Integer id) throws Exception{
        try (DataScope ds = new DataScope()){
            EntityManager em  = ds.getEntityManager();
            Conversa conversa = em.find(Conversa.class, id);
            ds.validateWork();
            return conversa;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void Update(Conversa entity) throws Exception{
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Conversa c = em.find(Conversa.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
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
    public void Delete(Conversa entity) throws Exception{
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            Conversa c = em.find(Conversa.class, entity, LockModeType.PESSIMISTIC_WRITE );
            if (c == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(c);
            ds.validateWork();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
