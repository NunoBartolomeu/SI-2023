package model.jogador;

import jakarta.persistence.*;
import model.DataScope;
import model.Mapper;

import java.util.List;

public class JogadorMapper implements Mapper<Jogador, Integer> {
    @Override
    public void Create(Jogador entity) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            ds.validateWork();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public Jogador Read(Integer id) throws Exception{
        try (DataScope ds = new DataScope()){
            EntityManager em = ds.getEntityManager();
            em.flush();
            Jogador j = em.find(Jogador.class, id);
            ds.validateWork();
            return j;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void Update(Jogador entity) throws Exception{
        try (DataScope ds = new DataScope()){
            EntityManager em  = ds.getEntityManager();
            em.flush();
            em.getTransaction().begin();
            Jogador j = em.find(Jogador.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (j == null)
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
    public void Delete(Jogador entity) throws Exception{
        try (DataScope ds  = new DataScope()){
            EntityManager em = ds.getEntityManager();
            em.flush();
            em.getTransaction().begin();
            Jogador j = em.find(Jogador.class, entity.getId(), LockModeType.PESSIMISTIC_WRITE );
            if (j == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(j);
            ds.validateWork();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Jogador findByUsername(String username) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            Query query = em.createQuery("SELECT j FROM jogadores j WHERE j.username = :username");
            query.setParameter("username", username);
            List<Jogador> jogadorList = query.getResultList();

            if (!jogadorList.isEmpty()) {
                return jogadorList.get(0);
            }

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return null;
    }
}