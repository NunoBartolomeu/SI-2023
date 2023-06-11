package exercises;

import org.junit.Test;
import com.sun.codemodel.JStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import model.DataScope;
import model.jogador_total_info.JogadorTotalInfo;
import model.jogador_total_info.JogadorTotalInfoRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

public class Exercise1_A {

    // Exercise D - criarJogador
    @Test
    public static void criarJogador(String playerEmail, String playerUsername) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("criarJogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, playerEmail);
            query.setParameter(2, playerUsername);
            query.execute();

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise D - desativarJogador
    @Test
    public static void desativarJogador(String playerEmail, String playerUsername) throws Exception {
        try (DataScope ds  =  new DataScope()) {
            EntityManager em  =  ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("desativarJogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, playerEmail);
            query.setParameter(2, playerUsername);
            query.executeUpdate();

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise D - banirJogador
    @Test
    public static void banirJogador(String playerEmail, String playerUsername) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("banirJogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, playerEmail);
            query.setParameter(2, playerUsername);
            query.execute();

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise E - totalPontosJogador
    @Test
    public static int totalPontosJogador(int jogadorId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("totalPontosJogador");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.setParameter(1, jogadorId);
            query.execute();

            Object result = query.getSingleResult();
            int totalPontos = ((Object[]) result)[0] instanceof Integer ? (int) ((Object[]) result)[0] : 0;

            ds.validateWork();
            return totalPontos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise F - totalJogosJogador
    @Test
    public static int totalJogosJogador(int jogadorId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("totalJogosJogador");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.setParameter(1, jogadorId);
            query.execute();

            Object result = query.getSingleResult();
            int totalJogos = ((Object[]) result)[0] instanceof Integer ? (int) ((Object[]) result)[0] : 0;

            ds.validateWork();
            return totalJogos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise G - PontosJogoPorJogador
    @Test
    public static List<Object[]> pontosJogoPorJogador(String referenciaJogo) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("PontosJogoPorJogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.setParameter(1, referenciaJogo);
            query.execute();

            ds.validateWork();
            return (List<Object[]>) query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise H - associarCracha
    @Test
    public static void associarCracha(int jogadorId, String jogoId, String crachaNome) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            Connection connection = em.unwrap(Connection.class);

            try (CallableStatement statement = connection.prepareCall("CALL associarCracha(?, ?, ?)")) {
                statement.setInt(1, jogadorId);
                statement.setString(2, jogoId);
                statement.setString(3, crachaNome);
                statement.execute();
            }

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    // Exercise I - iniciarConversa
    @Test
    public static int iniciarConversa(int jogadorId, String nomeConversa) throws Exception {

        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            Connection connection = em.unwrap(Connection.class);

            int idConversa;

            try (CallableStatement statement = connection.prepareCall("CALL iniciarConversa(?, ?, ?)")) {
                statement.registerOutParameter(3, Types.INTEGER);
                statement.setInt(1, jogadorId);
                statement.setString(2, nomeConversa);
                statement.execute();
                idConversa = statement.getInt(3);
            }
            ds.validateWork();
            return idConversa;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        /*try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("iniciarConversa");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
            query.setParameter(1, jogadorId);
            query.setParameter(2, nomeConversa);
            query.execute();
            idConversa[0] = (int) query.getOutputParameterValue(3);

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

         */
    }

    // Exercise J - juntarConversa
    @Test
    public static void juntarConversa(int jogadorId, int conversaId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("juntarConversa");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.setParameter(1, jogadorId);
            query.setParameter(2, conversaId);
            query.execute();

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    // Exercise K - enviarMensagem
    @Test
    public static void enviarMensagem(int jogadorId, int conversaId, String mensagemTexto) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            StoredProcedureQuery query = em.createStoredProcedureQuery("enviarMensagem");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            query.setParameter(1, jogadorId);
            query.setParameter(2, conversaId);
            query.setParameter(3, mensagemTexto);
            query.execute();

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise L - jogadorTotalInfo
    @Test
    public static List<JogadorTotalInfo> getJogadorTotalInfo() {
        try (DataScope ds = new DataScope()) {
            JogadorTotalInfoRepository j = new JogadorTotalInfoRepository();
            List<JogadorTotalInfo> list = j.GetAll();
            ds.validateWork();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

