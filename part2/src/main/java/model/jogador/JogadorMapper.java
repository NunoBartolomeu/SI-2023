package model.jogador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Mapper;
import model.jogador.Jogador;

public class JogadorMapper implements Mapper<Jogador, Integer> {
    @PersistenceContext
    private EntityManager em;


    @Override
    public void Create(Jogador entity) {
        em.persist(entity);
    }

    @Override
    public Jogador Read(Integer id) {
        return em.find(Jogador.class, id);
    }

    @Override
    public void Delete(Jogador entity) {
        em.remove(entity);
    }

    @Override
    public void Update(Jogador entity) {
        em.merge(entity);
    }
}
