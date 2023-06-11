package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.DataScope;
import model.cracha.Cracha;
import model.jogador.Jogador;

import java.util.List;
import java.util.Objects;

public class Exercise1_C {
    public void associarCracha(int jogadorId, String jogoId, String crachaNome) throws Exception {
        try (DataScope ds = new DataScope()){
            EntityManager em = ds.getEntityManager();

            // Verificar se o jogador atingiu o limite de pontos para obter o crachá
            List<Object[]> leaderboard = em.createNamedStoredProcedureQuery("PontosJogoPorJogador")
                    .setParameter("referencia_jogo", jogoId)
                    .getResultList();

            Integer pontosJogador = null;
            for (Object[] row : leaderboard) {
                Integer playerId = (Integer) row[0];
                Long playerPoints = (Long) row[1];
                if (Objects.equals(playerId, jogadorId)) {
                    pontosJogador = playerPoints.intValue();
                    break;
                }
            }

            if (pontosJogador == null) {
                System.out.println("O jogador não está no leaderboard para esse jogo!");
            } else {
                // Verificar se o jogador atingiu o limite de pontos para obter o crachá
                Cracha cracha = em.createQuery("SELECT c FROM Cracha c WHERE c.id_jogo = :jogoId AND c.nome = :crachaNome", Cracha.class)
                        .setParameter("jogoId", jogoId)
                        .setParameter("crachaNome", crachaNome)
                        .getSingleResult();
                int limitePontosCracha = cracha.getLimitePontos();

                if (pontosJogador < limitePontosCracha) {
                    System.out.println("O jogador não atingiu o limite de pontos para obter o crachá!");
                } else {
                    // Verificar se o jogador já possui o crachá
                    Jogador jogador = em.find(Jogador.class, jogadorId);
                    boolean hasCracha = jogador.getCrachas().stream()
                            .anyMatch(c -> c.getId().getNome().equals(crachaNome) && c.getId().getIdJogo().equals(jogoId));

                    if (hasCracha) {
                        System.out.println("O jogador já possui esse crachá!");
                    } else {
                        // Create a new Cracha object and associate it with the jogador
                        jogador.addCracha(cracha);
                        em.merge(jogador);
                        em.getTransaction().commit();
                        System.out.println("Crachá Obtido!");
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
