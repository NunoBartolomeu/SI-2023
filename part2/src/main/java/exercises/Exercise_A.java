package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;

public class Exercise_A {
    private EntityManager em;

    public Exercise_A(EntityManager em) {
        this.em = em;
    }

    // Exercise D - criarJogador
    public void criarJogador(String playerEmail, String playerUsername) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("criarJogador");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(1, playerEmail);
        query.setParameter(2, playerUsername);
        query.execute();
    }

    // Exercise D - desativarJogador
    public void desativarJogador(String playerEmail, String playerUsername) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("desativarJogador");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(1, playerEmail);
        query.setParameter(2, playerUsername);
        query.execute();
    }

    // Exercise D - banirJogador
    public void banirJogador(String playerEmail, String playerUsername) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("banirJogador");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(1, playerEmail);
        query.setParameter(2, playerUsername);
        query.execute();
    }

    // Exercise E - totalPontosJogador
    public int totalPontosJogador(int jogadorId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("totalPontosJogador");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, jogadorId);
        query.execute();
        return (int) query.getSingleResult();
    }

    // Exercise F - totalJogosJogador
    public int totalJogosJogador(int jogadorId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("totalJogosJogador");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, jogadorId);
        query.execute();
        return (int) query.getSingleResult();
    }

    // Exercise G - PontosJogoPorJogador
    public List<Object[]> pontosJogoPorJogador(String referenciaJogo) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("PontosJogoPorJogador");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.setParameter(1, referenciaJogo);
        query.execute();
        return (List<Object[]>) query.getResultList();
    }

    // Exercise H - associarCracha
    public void associarCracha(int jogadorId, String jogoId, String crachaNome) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("associarCracha");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.setParameter(1, jogadorId);
        query.setParameter(2, jogoId);
        query.setParameter(3, crachaNome);
        query.execute();
    }

    // Exercise I - iniciarConversa
    public void iniciarConversa(int jogadorId, String nomeConversa, int[] idConversa) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("iniciarConversa");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query.setParameter(1, jogadorId);
        query.setParameter(2, nomeConversa);
        query.execute();
        idConversa[0] = (int) query.getOutputParameterValue(3);
    }

    // Exercise J - juntarConversa
    public void juntarConversa(int jogadorId, int conversaId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("juntarConversa");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.setParameter(1, jogadorId);
        query.setParameter(2, conversaId);
        query.execute();
    }

    // Exercise K - enviarMensagem
    public void enviarMensagem(int jogadorId, int conversaId, String mensagemTexto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("enviarMensagem");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.setParameter(1, jogadorId);
        query.setParameter(2, conversaId);
        query.setParameter(3, mensagemTexto);
        query.execute();
    }

    // Exercise L - jogadorTotalInfo
    public List<Object[]> getJogadorTotalInfo() {
        return em.createQuery("SELECT j.id, j.estado, j.email, j.username, COUNT(DISTINCT p.idJogo), COUNT(p.idPartida), SUM(p.pontos) " +
                        "FROM Jogadores j " +
                        "INNER JOIN Pontuacoes p ON j.id = p.idJogador " +
                        "WHERE j.estado != 'banido' " +
                        "GROUP BY j.id, j.estado, j.email, j.username")
                .getResultList();
    }
}
