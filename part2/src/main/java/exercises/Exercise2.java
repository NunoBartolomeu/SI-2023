package exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.DataScope;
import model.cracha.Cracha;
import model.cracha.CrachaId;
import model.cracha.CrachaMapper;

public class Exercise2 {
    public static void aumentarPontosEm20PorcentoOtimista(String idJogo, String nomeCracha) throws Exception {
        CrachaId crachaId = new CrachaId();
        crachaId.setIdJogo(idJogo);
        crachaId.setNome(nomeCracha);
        CrachaMapper cm = new CrachaMapper();

        try (DataScope ds = new DataScope()) {
            Cracha cracha = cm.Read(crachaId);
            //Sleep needed to ensure collision
            Thread.sleep(100);
            cracha.setLimitePontos((int) (cracha.getLimitePontos() * 1.2));

            ds.validateWork();
        }
    }

    public static void aumentarPontosEm20PorcentoPessimista(String idJogo, String nomeCracha) throws Exception {
        CrachaId crachaId = new CrachaId();
        crachaId.setIdJogo(idJogo);
        crachaId.setNome(nomeCracha);

        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            Cracha cracha = em.find(Cracha.class, crachaId, LockModeType.PESSIMISTIC_READ);
            // Sleep needed to ensure collision
            Thread.sleep(100);
            cracha.setLimitePontos((int) (cracha.getLimitePontos() * 1.2));

            ds.validateWork();
        }
    }


    public static void reiniciarPontos(String idJogo, String nomeCracha, int pontos) throws Exception {
        CrachaId crachaId = new CrachaId();
        crachaId.setIdJogo(idJogo);
        crachaId.setNome(nomeCracha);
        CrachaMapper cm = new CrachaMapper();
        try (DataScope ds = new DataScope()) {

            Cracha cracha = cm.Read(crachaId);

            cracha.setLimitePontos(pontos);

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
