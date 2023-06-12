package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import model.DataScope;

import java.math.BigDecimal;

public class Extras {
    public static class JogadorPontuacao {
        private int idJogador;
        private BigDecimal pontos;

        public JogadorPontuacao(int idJogador, BigDecimal pontos) {
            this.idJogador = idJogador;
            this.pontos = pontos;
        }

        public int getIdJogador() {
            return idJogador;
        }

        public BigDecimal getPontos() {
            return pontos;
        }
    }

    public static void deleteJogadorParaTestes(String email){
        try(DataScope ds = new DataScope()){
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("delete_jogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.setParameter(1, email);
            query.execute();
            ds.validateWork();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
