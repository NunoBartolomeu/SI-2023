package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;

import model.DataScope;
import model.cracha.Cracha;
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;
import model.jogador.Jogador;

public class Exercise_B {
    private EntityManagerFactory emf;
    private EntityManager em;

    public void associarCracha(int jogadorId, String jogoId, String crachaNome) {
        try (DataScope ds = new DataScope()) {
            em = ds.getEntityManager();

            // Verificar se o jogador atingiu o limite de pontos para obter o crachá
            CrachaMapper crachaMapper = new CrachaMapper();
            CrachaId crachaId = new CrachaId();
            crachaId.setNome(crachaNome);
            crachaId.setIdJogo(jogoId);
            Cracha cracha = crachaMapper.Read(crachaId);
            int limitePontosCracha = cracha.getLimitePontos();

            // Pontos totais do jogador nesse jogo
            Integer pontosJogador = em.createQuery(
                            "SELECT SUM(p.pontos) FROM Pontuacao p WHERE p.id_jogador = :jogadorId AND p.id_jogo = :jogoId", Integer.class)
                    .setParameter("jogadorId", jogadorId)
                    .setParameter("jogoId", jogoId)
                    .getSingleResult();

            if (pontosJogador < limitePontosCracha) {
                System.out.println("O jogador não atingiu o limite de pontos para obter o crachá!");
            }

            else {
                // Verificar se o jogador já possui o crachá
                Jogador jogador = em.find(Jogador.class, jogadorId);
                boolean hasCracha = jogador.getCrachas().stream()
                        .anyMatch(c -> c.getId().getNome().equals(crachaNome) && c.getId().getIdJogo().equals(jogoId));

                if (hasCracha) {
                    System.out.println("O jogador já possui esse crachá!");
                }

                else {
                    // Create a new Cracha object and associate it with the jogador
                    jogador.addCracha(cracha);
                    em.merge(jogador);
                    em.getTransaction().commit();
                    System.out.println("Crachá Obtido!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
