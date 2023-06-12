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
            em.persist(entity);

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
                ds.validateWork();
                return jogadorList.get(0);
            }

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return null;
    }

    public void deleteByUsername(String username) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Query query = em.createQuery("Select j FROM jogadores j WHERE j.username = :username");
            query.setParameter("username", username);
            List<Jogador> jogadorList = query.getResultList();
            int id = jogadorList.get(0).getId();

            if (!jogadorList.isEmpty()) {
                Query deleteStatsQuery = em.createQuery("DELETE FROM estatisticas_jogador s WHERE s.id_jogador = :id");
                Query deleteJogadorQuery = em.createQuery("DELETE FROM jogadores j WHERE j.username = :username");
                deleteStatsQuery.setParameter("id", id);
                deleteJogadorQuery.setParameter("username", username);
                deleteStatsQuery.executeUpdate();
                deleteJogadorQuery.executeUpdate();

                ds.validateWork();
            }

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}