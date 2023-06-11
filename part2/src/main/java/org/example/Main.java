package org.example;

import exercises.Exercise1_A;
import jakarta.persistence.EntityManager;
import model.DataScope;
import model.jogador.JogadorMapper;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try (DataScope ds = new DataScope()) {
            JogadorMapper jm = new JogadorMapper();
            EntityManager em = ds.getEntityManager();
            Exercise1_A ea = new Exercise1_A();

            //System.out.println(jm.findByUsername("Rui").getEstado());
            Exercise1_A.desativarJogador("rui@themail.com", "Rui");
            System.out.println(jm.findByUsername("Rui").getEstado());
            if (Objects.equals(jm.findByUsername("Rui").getEstado(), "inativo")) {
                System.out.println("testeA_D_desativarJogador() OK");
            }
            else
                System.out.println("testeA_D_desativarJogador() NOK");

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
