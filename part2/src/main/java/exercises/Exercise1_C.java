package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.DataScope;
import model.cracha.Cracha;
<<<<<<< HEAD:part2/src/main/java/exercises/Exercise1_C.java
=======
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;
>>>>>>> 04e6842f022c7363d69600c41dca7d34c12f9082:part2/src/main/java/exercises/Exercise_C.java
import model.jogador.Jogador;
import model.jogador.JogadorMapper;

import java.util.List;
import java.util.Objects;

<<<<<<< HEAD:part2/src/main/java/exercises/Exercise1_C.java
public class Exercise1_C {
    public void associarCracha(int jogadorId, String jogoId, String crachaNome) throws Exception {
        try (DataScope ds = new DataScope()){
            EntityManager em = ds.getEntityManager();
=======
public class Exercise_C {
    private EntityManagerFactory emf;
    private EntityManager em;

    public String associarCracha(int jogadorId, String jogoId, String crachaNome) {
        try {
            emf = Persistence.createEntityManagerFactory("JPA_SI");
            em = emf.createEntityManager();
            em.getTransaction().begin();
>>>>>>> 04e6842f022c7363d69600c41dca7d34c12f9082:part2/src/main/java/exercises/Exercise_C.java


            // Verificar se o jogador atingiu o limite de pontos para obter o crachá
            CrachaMapper crachaMapper = new CrachaMapper();
            CrachaId crachaId = new CrachaId();
            crachaId.setNome(crachaNome);
            crachaId.setIdJogo(jogoId);
            Cracha cracha = crachaMapper.Read(crachaId);

            int limitePontos = cracha.getLimitePontos();
            System.out.println("LIMITE PONTOS : "+limitePontos);

            List<Object[]> leaderboard = Exercise_A.pontosJogoPorJogador(jogoId);

            Integer pontosJogador = null;
            for (Object[] row : leaderboard) {
                Integer playerId = (Integer) row[0];
                String playerPoints = row[1].toString();
                System.out.println("player : "+playerId);
                System.out.println("Jogador :"+jogadorId);
                if (Objects.equals(playerId, jogadorId)) {
                    pontosJogador = Integer.valueOf(playerPoints);
                    break;
                }
            }

            System.out.println("Pontos Player : "+pontosJogador);

            if (pontosJogador == null) {
                return "O jogador não está no leaderboard para esse jogo!";
            } else {
                // Verificar se o jogador atingiu o limite de pontos para obter o crachá
                /*Cracha cracha = em.createQuery("SELECT c FROM Cracha c WHERE c.id_jogo = :jogoId AND c.nome = :crachaNome", Cracha.class)
                        .setParameter("jogoId", jogoId)
                        .setParameter("crachaNome", crachaNome)
                        .getSingleResult();
                int limitePontosCracha = cracha.getLimitePontos()
                 */

                if (pontosJogador < limitePontos) {
                    return "O jogador não atingiu o limite de pontos para obter o crachá!";
                } else {
                    // Verificar se o jogador já possui o crachá
                    //Jogador jogador = em.find(Jogador.class, jogadorId);
                    JogadorMapper jogadorMapper = new JogadorMapper();
                    Jogador jogador = jogadorMapper.Read(jogadorId);

                    System.out.println("Jogador : "+jogador);

                    boolean hasCracha = jogador.getCrachas().stream()
                            .anyMatch(c -> c.getId().getNome().equals(crachaNome) && c.getId().getIdJogo().equals(jogoId));

                    System.out.println("HasCracha : "+hasCracha );
                    if (hasCracha) {
                        return "O jogador já possui esse crachá!";
                    } else {
                        // Create a new Cracha object and associate it with the jogador
                        jogador.addCracha(cracha);
                        em.merge(jogador);
                        em.getTransaction().commit();
                        return "Crachá Obtido!";
                    }
                }
            }
<<<<<<< HEAD:part2/src/main/java/exercises/Exercise1_C.java
        }
        catch (Exception e) {
=======

        } catch (Exception e) {
>>>>>>> 04e6842f022c7363d69600c41dca7d34c12f9082:part2/src/main/java/exercises/Exercise_C.java
            System.out.println(e.getMessage());
            throw e;
        }
        return "ERRO";
    }
}
