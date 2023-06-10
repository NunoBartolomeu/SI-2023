package exercises;

import model.DataScope;
import model.conversa.ConversaMapper;
import model.jogador.JogadorMapper;

import java.util.List;
import java.util.Objects;

public class Test_A {
    static JogadorMapper jm = new JogadorMapper();
    static ConversaMapper cm = new ConversaMapper();

    public static void main(String[] args) throws Exception {
        testeA_D();
        testeA_E();
        testeA_F();
        testeA_G();
        testeA_H();
        testeA_I();
        testeA_J();
        testeA_K();
        testeA_L();
    }

    private static void testeA_D() throws Exception {
        try (DataScope ds = new DataScope()) {
            Exercise_A.criarJogador("TestEmail@gmail.com", "testUsername");
            if (Objects.equals(jm.findByUsername("testUsername").getEstado(), "ativo")) {
                System.out.println("testeA_D_criarJogador() OK");
            }
            else
                System.out.println("testeA_D_criarJogador() NOK");

            Exercise_A.desativarJogador("TestEmail@gmail.com", "testUsername");
            if (Objects.equals(jm.findByUsername("testUsername").getEstado(), "inativo")) {
                System.out.println("testeA_D_desativarJogador() OK");
            }
            else
                System.out.println("testeA_D_desativarJogador() NOK");

            Exercise_A.banirJogador("TestEmail@gmail.com", "testUsername");
            if (Objects.equals(jm.findByUsername("testUsername").getEstado(), "banido")) {
                System.out.println("testeA_D_banirJogador() OK");
            }
            else
                System.out.println("testeA_D_banirJogador() NOK");

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_E() throws Exception {
        try (DataScope ds = new DataScope()) {
            if (Exercise_A.totalPontosJogador(4) == 400) {
                System.out.println("testeA_E_totalPontosJogador() OK");
            }
            else
                System.out.println("testeA_E_totalPontosJogador() NOK");

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_F() throws Exception {
        try (DataScope ds = new DataScope()) {
            if (Exercise_A.totalJogosJogador(4) == 2) {
                System.out.println("testeA_F_totalPontosJogador() OK");
            }
            else
                System.out.println("testeA_F_totalPontosJogador() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_G() throws Exception {
        try (DataScope ds = new DataScope()) {
            List<Object[]> r = Exercise_A.pontosJogoPorJogador("LOL1234567");

            System.out.println(r.get(0));
            if (r.get(0).toString() == "") {
                System.out.println("testeA_G_pontosJogoPorJogador() OK");
            }
            else
                System.out.println("testeA_G_pontosJogoPorJogador() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_H() throws Exception {
        try (DataScope ds = new DataScope()) {
            Exercise_A.associarCracha(4, "LOL1234567", "begin");
            if(jm.Read(4).getCrachas().stream()
                    .anyMatch(cracha -> cracha.getId().getIdJogo().equals("LOL1234567") && cracha.getId().getNome().equals("begin"))) {
                System.out.println("testeA_H_associarCracha() OK");
            }
            else
                System.out.println("testeA_H_associarCracha() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_I() throws Exception {
        try (DataScope ds = new DataScope()) {
            int[] idConversa = new int[1];
            Exercise_A.iniciarConversa(4, "Conversa Teste", idConversa);
            if(cm.Read(idConversa[0]).getNome() == "Conversa Teste") {
                System.out.println("testeA_I_iniciarConversa() OK");
            }
            else
                System.out.println("testeA_I_iniciarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_J() throws Exception {
        try (DataScope ds = new DataScope()) {
            int[] idConversa = new int[1];
            Exercise_A.iniciarConversa(4, "Conversa Teste", idConversa);
            Exercise_A.juntarConversa(2, idConversa[0]);

            if(cm.Read(idConversa[0]).getParticipantes().stream()
                    .anyMatch(jogador -> jogador.getId() == 2)) {
                System.out.println("testeA_J_juntarConversa() OK");
            }
            else
                System.out.println("testeA_J_juntarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_K() throws Exception {
        try (DataScope ds = new DataScope()) {
            int[] idConversa = new int[1];
            Exercise_A.iniciarConversa(4, "Conversa Teste", idConversa);
            Exercise_A.juntarConversa(2, idConversa[0]);
            Exercise_A.enviarMensagem(2, idConversa[0], "Test Message");

            if(cm.Read(idConversa[0]).getMensagens().stream()
                    .anyMatch(mensagem -> Objects.equals(mensagem.getTexto(), "Test Message"))) {
                System.out.println("testeA_J_juntarConversa() OK");
            }
            else
                System.out.println("testeA_J_juntarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private static void testeA_L() throws Exception {
        try (DataScope ds = new DataScope()) {
            List<Object[]> lista = Exercise_A.getJogadorTotalInfo();

            if(Objects.equals(lista.get(0).toString(), "")) {
                System.out.println("testeA_J_juntarConversa() OK");
            }
            else
                System.out.println("testeA_J_juntarConversa() NOK");
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}