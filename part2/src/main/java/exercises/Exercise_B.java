package exercises;

import jakarta.persistence.EntityManager;
import model.DataScope;
import model.cracha.Cracha;
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;
import model.jogador.Jogador;
import model.jogador.JogadorMapper;
import model.partida_multijogador.PartidaMultijogador;
import model.partida_normal.PartidaNormal;
import model.pontuacão.PontuacaoId;
import model.pontuacão.PontuacaoMultijogadorMapper;
import model.pontuacão.Pontuacao_Multi_Jogador;

public class Exercise_B {
    public String associarCracha(int jogadorId, String jogoId, String crachaNome) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            // Verificar se o jogador atingiu o limite de pontos para obter o crachá
            CrachaMapper crachaMapper = new CrachaMapper();
            CrachaId crachaId = new CrachaId();
            crachaId.setNome(crachaNome);
            crachaId.setIdJogo(jogoId);
            Cracha cracha = crachaMapper.Read(crachaId);

            int limitePontosCracha = cracha.getLimitePontos();
            int pontosJogador = 0;
            // Pontos totais do jogador nesse jogo

            JogadorMapper jm = new JogadorMapper();
            Jogador jogador = jm.Read(jogadorId);

            for (Pontuacao_Multi_Jogador pm : jogador.getPartidasMultijogador()){
                if (pm.getId().getIdJogo().equals(jogoId))
                    pontosJogador+=pm.getPontos();
            }

            for (PartidaNormal pm : jogador.getPartidasNormais()){
                if (pm.getId().getIdJogo().equals(jogoId))
                    pontosJogador+=pm.getPontos();
            }

            System.out.println("Pontos : " + pontosJogador);


            if (pontosJogador < limitePontosCracha) {
                return("O jogador não atingiu o limite de pontos para obter o crachá!");
            }

            else {
                // Verificar se o jogador já possui o crachá

                boolean hasCracha = jogador.getCrachas().stream()
                        .anyMatch(c -> c.getId().getNome().equals(crachaNome) && c.getId().getIdJogo().equals(jogoId));

                if (hasCracha) {
                    return ("O jogador já possui esse crachá!");
                }

                else {
                    // Create a new Cracha object and associate it with the jogador
                    jogador.addCracha(cracha);
                    jm.Update(jogador);
                    return ("Crachá Obtido!");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
