package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import model.DataScope;
import model.cracha.Cracha;
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;
import model.jogador.Jogador;
import model.jogador_total_info.JogadorTotalInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Exercise1_C {
    public static String associarCracha(int jogadorId, String jogoId, String crachaNome) throws Exception {
        try (DataScope ds = new DataScope()){
            EntityManager em = ds.getEntityManager();

            // Verificar se o jogador atingiu o limite de pontos para obter o cracha
            StoredProcedureQuery query = em.createStoredProcedureQuery("PontosJogoPorJogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.setParameter(1, jogoId);
            query.execute();

            List<Object[]> leaderboard = query.getResultList();
            System.out.println("leadboard : "+leaderboard);
            Integer pontosJogador = null;
            for (Object[] row : leaderboard) {
                Integer playerId = (Integer) row[0];
                BigDecimal playerPoints = (BigDecimal) row[1];
                if (Objects.equals(playerId, jogadorId)) {
                    pontosJogador = playerPoints.intValue();
                    break;
                }
            }
            System.out.println("Pontos jogador : "+pontosJogador);

            if (pontosJogador == null) {
                return ("O jogador nao esta no leaderboard para esse jogo!");
            } else {
                CrachaMapper crachaMapper = new CrachaMapper();
                CrachaId crachaId = new CrachaId();
                crachaId.setNome(crachaNome);
                crachaId.setIdJogo(jogoId);
                Cracha cracha = crachaMapper.Read(crachaId);

                int limitePontosCracha = cracha.getLimitePontos();

                if (pontosJogador < limitePontosCracha) {
                    return ("O jogador nao atingiu o limite de pontos para obter o cracha!");
                } else {
                    // Verificar se o jogador ja possui o cracha
                    Jogador jogador = em.find(Jogador.class, jogadorId);
                    boolean hasCracha = jogador.getCrachas().stream()
                            .anyMatch(c -> c.getId().getNome().equals(crachaNome) && c.getId().getIdJogo().equals(jogoId));

                    if (hasCracha) {
                        ds.validateWork();
                        return ("O jogador ja possui esse cracha!");
                    } else {
                        // Create a new Cracha object and associate it with the jogador
                        jogador.addCracha(cracha);
                        em.merge(jogador);
                        ds.validateWork();
                        return ("Cracha Obtido!");
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
