package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;

public class Test_A {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
        EntityManager em = emf.createEntityManager();

        // Exercise D - criarJogador using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery criarJogadorQuery = em.createStoredProcedureQuery("criarJogador");
        criarJogadorQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        criarJogadorQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        criarJogadorQuery.setParameter(1, "player@example.com");
        criarJogadorQuery.setParameter(2, "player123");
        criarJogadorQuery.execute();
        em.getTransaction().commit();

        // Exercise D - desativarJogador using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery desativarJogadorQuery = em.createStoredProcedureQuery("desativarJogador");
        desativarJogadorQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        desativarJogadorQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        desativarJogadorQuery.setParameter(1, "player@example.com");
        desativarJogadorQuery.setParameter(2, "player123");
        desativarJogadorQuery.execute();
        em.getTransaction().commit();

        // Exercise D - banirJogador using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery banirJogadorQuery = em.createStoredProcedureQuery("banirJogador");
        banirJogadorQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        banirJogadorQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        banirJogadorQuery.setParameter(1, "player@example.com");
        banirJogadorQuery.setParameter(2, "player123");
        banirJogadorQuery.execute();
        em.getTransaction().commit();

        // Exercise E - totalPontosJogador
        em.getTransaction().begin();
        StoredProcedureQuery totalPontosJogadorQuery = em.createStoredProcedureQuery("totalPontosJogador");
        totalPontosJogadorQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        totalPontosJogadorQuery.setParameter(1, 1);
        int totalPontos = (int) totalPontosJogadorQuery.getSingleResult();
        System.out.println("Total Pontos Jogador: " + totalPontos);
        em.getTransaction().commit();

        // Exercise F - totalJogosJogador
        em.getTransaction().begin();
        StoredProcedureQuery totalJogosJogadorQuery = em.createStoredProcedureQuery("totalJogosJogador");
        totalJogosJogadorQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        totalJogosJogadorQuery.setParameter(1, 4);
        int totalJogos = (int) totalJogosJogadorQuery.getSingleResult();
        System.out.println("Total Jogos Jogador: " + totalJogos);
        em.getTransaction().commit();

        // Exercise G - pontosJogoPorJogador
        em.getTransaction().begin();
        StoredProcedureQuery pontosJogoPorJogadorQuery = em.createStoredProcedureQuery("pontosJogoPorJogador");
        pontosJogoPorJogadorQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        pontosJogoPorJogadorQuery.setParameter(1, "LOL1234567");
        List<Object[]> pontosJogador = pontosJogoPorJogadorQuery.getResultList();
        for (Object[] ponto : pontosJogador) {
            int idJogador = (int) ponto[0];
            long totalPontoss = (long) ponto[1];
            System.out.println("Jogador ID: " + idJogador + ", Total Pontos: " + totalPontoss);
        }
        em.getTransaction().commit();

        // Exercise H - associarCracha using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery associarCrachaQuery = em.createStoredProcedureQuery("associarCracha");
        associarCrachaQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        associarCrachaQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        associarCrachaQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        associarCrachaQuery.setParameter(1, 1);
        associarCrachaQuery.setParameter(2, "LOL1234567");
        associarCrachaQuery.setParameter(3, "Cracha1");
        associarCrachaQuery.execute();
        em.getTransaction().commit();

        // Exercise I - iniciarConversa using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery iniciarConversaQuery = em.createStoredProcedureQuery("iniciarConversa");
        iniciarConversaQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        iniciarConversaQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        iniciarConversaQuery.setParameter(1, 1);
        iniciarConversaQuery.setParameter(2, "Conversa1");
        int idConversa = (int) iniciarConversaQuery.getSingleResult();
        System.out.println("ID Conversa: " + idConversa);
        em.getTransaction().commit();

        // Exercise J - juntarConversa using stored procedure query
        em.getTransaction().begin();
        StoredProcedureQuery juntarConversaQuery = em.createStoredProcedureQuery("juntarConversa");
        juntarConversaQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        juntarConversaQuery.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        juntarConversaQuery.setParameter(1, 1);
        juntarConversaQuery.setParameter(2, idConversa);
        juntarConversaQuery.execute();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}