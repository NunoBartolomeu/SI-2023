package exercises;

import model.DataScope;
import model.conversa.ConversaMapper;
import model.jogador.JogadorMapper;
import model.jogador_total_info.JogadorTotalInfo;

import java.util.List;
import java.util.Objects;

public class Tests {
    static JogadorMapper jm = new JogadorMapper();
    static ConversaMapper cm = new ConversaMapper();
    static Exercise1_A ea;

    public static void main(String[] args) throws Exception {

            teste2_Optimistic();
            //teste2_Pessimistic();
/*
            teste1_A_D();
            teste1_A_E();
            teste1_A_F();
            teste1_A_G();
            teste1_A_H();
            teste1_A_I();
            teste1_A_J();
            teste1_A_K();
            teste1_A_L();

            teste1_B();
            teste1_C();

            teste2_Async();
            teste2_Sync();

 */
    }

    private static void teste1_A_D() throws Exception {
        try (DataScope ds = new DataScope()) {
            Exercise1_A.criarJogador("TestEmail@gmail.com", "testUsername");
            if (Objects.equals(jm.findByUsername("testUsername").getEstado(), "ativo")) {
                System.out.println("testeA_D_criarJogador() OK");
            }
            else
                System.out.println("testeA_D_criarJogador() NOK");

            Exercise1_A.desativarJogador("TestEmail@gmail.com", "testUsername");
            if (Objects.equals(jm.findByUsername("testUsername").getEstado(), "inativo")) {
                System.out.println("testeA_D_desativarJogador() OK");
            }
            else
                System.out.println("testeA_D_desativarJogador() NOK");

            Exercise1_A.banirJogador("TestEmail@gmail.com", "testUsername");
            if (Objects.equals(jm.findByUsername("testUsername").getEstado(), "banido")) {
                System.out.println("testeA_D_banirJogador() OK");
            }
            else
                System.out.println("testeA_D_banirJogador() NOK");

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_E() throws Exception {
        try (DataScope ds = new DataScope()) {
            if (Exercise1_A.totalPontosJogador(4) == 1200) {
                System.out.println("testeA_E_totalPontosJogador() OK");
            }
            else
                System.out.println("testeA_E_totalPontosJogador() NOK");

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_F() throws Exception {
        try (DataScope ds = new DataScope()) {
            if (Exercise1_A.totalJogosJogador(4) == 2) {
                System.out.println("testeA_F_totalPontosJogador() OK");
            }
            else
                System.out.println("testeA_F_totalPontosJogador() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_G() throws Exception {
        try (DataScope ds = new DataScope()) {
            List<Object[]> r = Exercise1_A.pontosJogoPorJogador("LOL1234567");

            if (r.get(0).toString() == "") {
                System.out.println("testeA_G_pontosJogoPorJogador() OK");
            }
            else
                System.out.println("testeA_G_pontosJogoPorJogador() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_H() throws Exception {
        try (DataScope ds = new DataScope()) {
            Exercise1_A.associarCracha(4, "LOL1234567", "begin");
            if(jm.Read(4).getCrachas().stream()
                    .anyMatch(cracha -> cracha.getId().getIdJogo().equals("LOL1234567") && cracha.getId().getNome().equals("begin"))) {
                System.out.println("testeA_H_associarCracha() OK");
            }
            else
                System.out.println("testeA_H_associarCracha() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_I() throws Exception {
        try (DataScope ds = new DataScope()) {
            int idConversa = Exercise1_A.iniciarConversa(4, "Conversa Teste");
            System.out.println("ID CONVERSA =============> "+idConversa);
            if(Objects.equals(cm.Read(idConversa).getNome(), "Conversa Teste")) {
                System.out.println("testeA_I_iniciarConversa() OK");
            }
            else
                System.out.println("testeA_I_iniciarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_J() throws Exception {
        try (DataScope ds = new DataScope()) {
            int idConversa = Exercise1_A.iniciarConversa(4, "Conversa Teste");
            Exercise1_A.juntarConversa(2, idConversa);

            if(cm.Read(idConversa).getParticipantes().stream()
                    .anyMatch(jogador -> jogador.getId() == 2)) {
                System.out.println("testeA_J_juntarConversa() OK");
            }
            else
                System.out.println("testeA_J_juntarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_K() throws Exception {
        try (DataScope ds = new DataScope()) {
            int idConversa = Exercise1_A.iniciarConversa(4, "Conversa Teste");
            Exercise1_A.juntarConversa(2, idConversa);
            Exercise1_A.enviarMensagem(2, idConversa, "Test Message");

            if(cm.Read(idConversa).getMensagens().stream()
                    .anyMatch(mensagem -> Objects.equals(mensagem.getTexto(), "Test Message"))) {
                System.out.println("testeA_J_juntarConversa() OK");
            }
            else
                System.out.println("testeA_J_juntarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_A_L() throws Exception {
        try (DataScope ds = new DataScope()) {
            List<JogadorTotalInfo> lista = Exercise1_A.getJogadorTotalInfo();

            if(Objects.equals(lista.get(0).toString(), "")) {
                System.out.println("testeA_J_juntarConversa() OK");
            }
            else
                System.out.println("testeA_J_juntarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_B() throws Exception {
        try (DataScope ds = new DataScope()) {
            Exercise1_B eb = new Exercise1_B();

            String s = eb.associarCracha(0, "LOL1234567", "begin");

            if(Objects.equals(s, "O jogador já possui esse crachá!")) {
                System.out.println("testeB_associarCracha() OK");
            }
            else
                System.out.println("testeB_associarCracha() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void teste1_C() throws Exception {
        try (DataScope ds = new DataScope()) {
            Exercise1_B eb = new Exercise1_B();

            String s = eb.associarCracha(0, "LOL1234567", "begin");

            if(Objects.equals(s, "O jogador já possui esse crachá!")) {
                System.out.println("testeC_associarCracha() OK");
            }
            else
                System.out.println("testeC_associarCracha() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static void teste2_Optimistic() throws Exception {
        Exercise2.reiniciarPontos("LOL1234567", "begin", 10);
        //System.out.println("PONTOS : "+Exercise2.getPontos("LOL1234567", "begin"));
        Thread t1 = new Thread(() -> {
            try {
                Exercise2.aumentarPontosEm20PorcentoOtimista("LOL1234567", "begin");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Exercise2.aumentarPontosEm20PorcentoOtimista("LOL1234567", "begin");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


        try(DataScope ds = new DataScope()) {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            ds.validateWork();
        }


        int pontos = Exercise2.getPontos("LOL1234567", "begin");
        System.out.println("PONTOS : "+pontos);
        if(pontos == 12) {
            System.out.println("teste2_A_aumentarPontosEm20PorcentoOtismista() OK");
        }
        else
            System.out.println("teste2_A_aumentarPontosEm20PorcentoOtimista() NOK");
    }


    public static void teste2_Pessimistic() throws Exception {

        Exercise2.reiniciarPontos("LOL1234567", "begin", 10);

        Thread t1 = new Thread(() -> {
            try (DataScope ds = new DataScope()) {
                Exercise2.aumentarPontosEm20PorcentoPessimista("LOL1234567", "begin");
                ds.validateWork();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try (DataScope ds = new DataScope()) {
                Exercise2.aumentarPontosEm20PorcentoPessimista("LOL1234567", "begin");
                ds.validateWork();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int pontos = Exercise2.getPontos("LOL1234567", "begin");

        if(pontos == 14) {
            System.out.println("teste2_A_aumentarPontosEm20PorcentoPessimista() OK");
        }
        else
            System.out.println("teste2_A_aumentarPontosEm20PorcentoPessimista() NOK");
    }
}