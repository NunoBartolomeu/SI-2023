package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.DataScope;
import model.cracha.Cracha;
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        try (DataScope dss = new DataScope()) {

            reiniciarPontos("LOL1234567", "begin", 10);

            Thread t1 = new Thread(() -> {
                try (DataScope ds = new DataScope()) {
                    Exercise2.aumentarPontosEm20PorcentoAssincrono("LOL1234567", "begin");
                    ds.validateWork();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });

            Thread t2 = new Thread(() -> {
                try (DataScope ds = new DataScope()) {
                    Exercise2.aumentarPontosEm20PorcentoAssincrono("LOL1234567", "begin");
                    ds.validateWork();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            dss.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int pontos = Exercise2.getPontos("LOL1234567", "begin");

        if (pontos == 12) {
            System.out.println("teste2_A_aumentarPontosEm20PorcentoAssincrono() OK");
        }
        else {
            System.out.println("teste2_A_aumentarPontosEm20PorcentoAssincrono() NOK");
        }
    }

    public static void aumentarPontosEm20PorcentoAssincrono(String idJogo, String nomeCracha) throws Exception {
        try (DataScope ds = new DataScope()) {

            CrachaId crachaId = new CrachaId();
            crachaId.setIdJogo(idJogo);
            crachaId.setNome(nomeCracha);

            CrachaMapper cm = new CrachaMapper();
            Cracha cracha = cm.Read(crachaId);
            cracha.setLimitePontos((int) (cracha.getLimitePontos() * 1.2));

            ds.validateWork();
        }
    }

    public static void aumentarPontosEm20PorcentoSincrono(String idJogo, String nomeCracha) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em  = ds.getEntityManager();

            CrachaId crachaId = new CrachaId();
            crachaId.setIdJogo(idJogo);
            crachaId.setNome(nomeCracha);

            Cracha cracha = em.find(Cracha.class, crachaId, LockModeType.PESSIMISTIC_READ);

            cracha.setLimitePontos((int) (cracha.getLimitePontos() * 1.2));

            ds.validateWork();
        }
    }


    public static void reiniciarPontos(String idJogo, String nomeCracha, int pontos) throws Exception {
        try (DataScope ds = new DataScope()) {

            CrachaId crachaId = new CrachaId();
            crachaId.setIdJogo(idJogo);
            crachaId.setNome(nomeCracha);

            CrachaMapper cm = new CrachaMapper();
            Cracha cracha = cm.Read(crachaId);

            cracha.setLimitePontos(pontos);

            cm.Update(cracha);
            ds.validateWork();
        }
    }

    public static int getPontos(String idJogo, String nomeCracha) throws Exception {
        try (DataScope ds = new DataScope()) {
            CrachaId crachaId = new CrachaId();
            crachaId.setIdJogo(idJogo);
            crachaId.setNome(nomeCracha);

            CrachaMapper cm = new CrachaMapper();
            Cracha cracha = cm.Read(crachaId);
            int limitePontos = cracha.getLimitePontos();

            ds.validateWork();

            return limitePontos;
        }
    }
}
