package exercises;

import model.DataScope;
import org.junit.Test;

import static org.junit.Assert.*;

public class Exercise2Test {
    @Test
    public void aumentarPontosEm20PorcentoOtimista() throws Exception {
        Exercise2.reiniciarPontos("LOL1234567", "begin", 10);

        Thread t1 = new Thread(() -> {
            try { Exercise2.aumentarPontosEm20PorcentoOtimista("LOL1234567", "begin"); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        });

        Thread t2 = new Thread(() -> {
            try { Exercise2.aumentarPontosEm20PorcentoOtimista("LOL1234567", "begin"); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        });

        try(DataScope ds = new DataScope()) {
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            ds.validateWork();
        }

        int pontos = Exercise2.getPontos("LOL1234567", "begin");
        //System.out.println("PONTOS : "+pontos);
        assertEquals(12, pontos);
    }

    @Test
    public void aumentarPontosEm20PorcentoPessimista() throws Exception {
        Exercise2.reiniciarPontos("LOL1234567", "begin", 10);

        Thread t1 = new Thread(() -> {
            try { Exercise2.aumentarPontosEm20PorcentoPessimista("LOL1234567", "begin"); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        });

        Thread t2 = new Thread(() -> {
            try { Exercise2.aumentarPontosEm20PorcentoPessimista("LOL1234567", "begin"); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        });

        try(DataScope ds = new DataScope()) {
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            ds.validateWork();
        }

        int pontos = Exercise2.getPontos("LOL1234567", "begin");
        //System.out.println("PONTOS : "+pontos);
        assertEquals(14, pontos);
    }
}