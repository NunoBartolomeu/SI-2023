package exercises;

import jakarta.persistence.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class Test_A {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
        EntityManager em = emf.createEntityManager();

        Exercise_A exercise = new Exercise_A(em);

        // Exercise D - criarJogador using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery criarJogadorQuery = em.createStoredProcedureQuery("criarJogador");
        criarJogadorQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        criarJogadorQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        criarJogadorQuery.setParameter(1, "player@example.com");
        criarJogadorQuery.setParameter(2, "player123");
        criarJogadorQuery.execute();
        em.getTransaction().commit();

        // Exercise D - desativarJogador using native query
        em.getTransaction().begin();
        em.createNativeQuery("Select desativarJogador(?1, ?2);")
                .setParameter(1, "player@example.com")
                .setParameter(2, "player123")
                .executeUpdate();
        em.getTransaction().commit();

        // Exercise D - banirJogador using JDBC CallableStatement
        em.getTransaction().begin();
        Connection connection = em.unwrap(Connection.class);
        try (CallableStatement statement = connection.prepareCall("call banirJogador(?, ?)")) {
            statement.setString(1, "player@example.com");
            statement.setString(2, "player123");
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.getTransaction().commit();
/*
        // Exercise E - totalPontosJogador
        int totalPontos = exercise.totalPontosJogador(1);
        System.out.println("Total Pontos Jogador: " + totalPontos);

        // Exercise F - totalJogosJogador
        int totalJogos = exercise.totalJogosJogador(4);
        System.out.println("Total Jogos Jogador: " + totalJogos);

        // Exercise G - pontosJogoPorJogador
        List<Object[]> pontosJogador = exercise.pontosJogoPorJogador("LOL1234567");
        for (Object[] ponto : pontosJogador) {
            int idJogador = (int) ponto[0];
            long totalPontoss = (long) ponto[1];
            System.out.println("Jogador ID: " + idJogador + ", Total Pontos: " + totalPontoss);
        }

        // Exercise H - associarCracha using native query
        em.getTransaction().begin();
        em.createNativeQuery("call associarCracha(?1, ?2, ?3)")
                .setParameter(1, 1)
                .setParameter(2, "LOL1234567")
                .setParameter(3, "Cracha1")
                .executeUpdate();
        em.getTransaction().commit();

        // Exercise I - iniciarConversa using stored procedure query
        int[] idConversa = new int[1];
        StoredProcedureQuery iniciarConversaQuery = em.createStoredProcedureQuery("iniciarConversa");
        iniciarConversaQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        iniciarConversaQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        iniciarConversaQuery.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        iniciarConversaQuery.setParameter(1, 1);
        iniciarConversaQuery.setParameter(2, "Conversa1");
        iniciarConversaQuery.execute();
        idConversa[0] = (int) iniciarConversaQuery.getOutputParameterValue(3);
        System.out.println("ID Conversa: " + idConversa[0]);

        // Exercise J - juntarConversa using native query
        em.getTransaction().begin();
        em.createNativeQuery("call juntarConversa(?1, ?2)")
                .setParameter(1, idConversa[0])
                .setParameter(2, 1)
                .executeUpdate();
        em.getTransaction().commit();


 */
        em.close();
        emf.close();
    }

}
