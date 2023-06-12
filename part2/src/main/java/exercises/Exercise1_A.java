package exercises;

import com.sun.codemodel.JStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import model.DataScope;
import model.jogador_total_info.JogadorTotalInfo;
import model.jogador_total_info.JogadorTotalInfoRepository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Exercise1_A {

    // Exercise D - criarJogador
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
    public static List<Extras.JogadorPontuacao> pontosJogoPorJogador(String referenciaJogo) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Query query = em.createNativeQuery("SELECT * FROM PontosJogoPorJogador(?)");
            query.setParameter(1, referenciaJogo);

            List<Object[]> resultList = query.getResultList();
            List<Extras.JogadorPontuacao> result = new ArrayList<>();
            for (Object[] o : resultList){
                result.add(0, new Extras.JogadorPontuacao((int) o[0], (BigDecimal) o[1]));
            }

            ds.validateWork();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    // Exercise H - associarCracha
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
    }

    // Exercise J - juntarConversa
    public static void juntarConversa(int jogadorId, int conversaId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Connection connection = em.unwrap(Connection.class);

            try (CallableStatement statement = connection.prepareCall("CALL juntarConversa(?, ?)")) {
                statement.setInt(1, jogadorId);
                statement.setInt(2, conversaId);
                statement.execute();

                ds.validateWork();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }

    // Exercise K - enviarMensagem
    public static void enviarMensagem(int jogadorId, int conversaId, String mensagemTexto) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Connection connection = em.unwrap(Connection.class);
            try (CallableStatement statement = connection.prepareCall("CALL enviarMensagem(?, ?, ?)")) {
                statement.setInt(1, jogadorId);
                statement.setInt(2, conversaId);
                statement.setString(3, mensagemTexto);
                statement.execute();
            }

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Exercise L - jogadorTotalInfo
    public static List<JogadorTotalInfo> getJogadorTotalInfo() {
        try (DataScope ds = new DataScope()) {
            JogadorTotalInfoRepository j = new JogadorTotalInfoRepository();
            System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJ : "+j);
            List<JogadorTotalInfo> list = j.GetAll();
            System.out.println("LIST ====> "+list.get(0).getIdJogador());
            ds.validateWork();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
