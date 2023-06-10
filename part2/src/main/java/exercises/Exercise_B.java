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
import model.pontuac�o.PontuacaoId;
import model.pontuac�o.PontuacaoMultijogadorMapper;
import model.pontuac�o.Pontuacao_Multi_Jogador;

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
                return("O jogador n�o atingiu o limite de pontos para obter o crach�!");
            }

            else {
                // Verificar se o jogador j� possui o crach�

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
