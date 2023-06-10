package exercises;

import jakarta.persistence.EntityManager;
import model.DataScope;
import model.cracha.Cracha;
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;
import model.jogador.Jogador;
import model.jogador.JogadorMapper;

public class Exercise_B {
    public String associarCracha(int jogadorId, String jogoId, String crachaNome) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            // Verificar se o jogador atingiu o limite de pontos para obter o crach�
            CrachaMapper crachaMapper = new CrachaMapper();
            CrachaId crachaId = new CrachaId();
            crachaId.setNome(crachaNome);
            crachaId.setIdJogo(jogoId);
            Cracha cracha = crachaMapper.Read(crachaId);

            int limitePontosCracha = cracha.getLimitePontos();

            // Pontos totais do jogador nesse jogo
            Integer pontosJogador = em.createQuery(
                            "SELECT SUM(p.pontos) FROM Pontuacoes_MultiJogador p WHERE p.id_jogador = :jogadorId AND p.id_jogo = :jogoId", Integer.class)
                    .setParameter("jogadorId", jogadorId)
                    .setParameter("jogoId", jogoId)
                    .getSingleResult();

            if (pontosJogador < limitePontosCracha) {
                return("O jogador n�o atingiu o limite de pontos para obter o crach�!");
            }

            else {
                // Verificar se o jogador j� possui o crach�
                JogadorMapper jm = new JogadorMapper();
                Jogador jogador = jm.Read(jogadorId);

                boolean hasCracha = jogador.getCrachas().stream()
                        .anyMatch(c -> c.getId().getNome().equals(crachaNome) && c.getId().getIdJogo().equals(jogoId));

                if (hasCracha) {
                    return ("O jogador j� possui esse crach�!");
                }

                else {
                    // Create a new Cracha object and associate it with the jogador
                    jogador.addCracha(cracha);
                    jm.Update(jogador);
                    return ("Crach� Obtido!");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
