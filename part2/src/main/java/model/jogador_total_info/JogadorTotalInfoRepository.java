package model.jogador_total_info;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.DataScope;
import model.Repository;

import java.util.List;

public class JogadorTotalInfoRepository implements Repository<JogadorTotalInfo, Integer> {
    @Override
    public List<JogadorTotalInfo> GetAll() {
        try (DataScope ds = new DataScope()) {
            EntityManager em  = ds.getEntityManager();

            Query query = em.createQuery("Select j from JogadorTotalInfo j", JogadorTotalInfo.class);
            List<JogadorTotalInfo> list = query.getResultList();


            ds.validateWork();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public JogadorTotalInfo Find(Integer k) {
        return null;
    }

    @Override
    public void Add(JogadorTotalInfo entity) {

    }

    @Override
    public void Delete(JogadorTotalInfo entity) {

    }

    @Override
    public void Save(JogadorTotalInfo e) {

    }
}
