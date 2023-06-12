package exercises;

import model.DataScope;
import model.conversa.ConversaMapper;
import model.jogador.Jogador;
import model.jogador.JogadorMapper;
import model.jogador_total_info.JogadorTotalInfo;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class Exercise1_ATest {

    @Test
    public void criarJogador() throws Exception {
        JogadorMapper jm = new JogadorMapper();

        try (DataScope ds = new DataScope()) {
            Exercise1_A.criarJogador("TestEmail@gmail.com", "testUsername");
            assertEquals("ativo", jm.findByUsername("testUsername").getEstado());

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void desativarJogador() throws Exception {
        JogadorMapper jm = new JogadorMapper();

        try (DataScope ds = new DataScope()) {
            Exercise1_A.desativarJogador("TestEmail@gmail.com", "testUsername");
            assertEquals("inativo", jm.findByUsername("testUsername").getEstado());

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void banirJogador() throws Exception {
        JogadorMapper jm = new JogadorMapper();

        try (DataScope ds = new DataScope()) {
            Exercise1_A.banirJogador("TestEmail@gmail.com", "testUsername");
            assertEquals("banido", jm.findByUsername("testUsername").getEstado());

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void totalPontosJogador() throws Exception {
        try (DataScope ds = new DataScope()) {
            assertEquals(1200, Exercise1_A.totalPontosJogador(4));
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void totalJogosJogador() throws Exception {
        try (DataScope ds = new DataScope()) {
            assertEquals(2, Exercise1_A.totalJogosJogador(4));
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void pontosJogoPorJogador() throws Exception {
        try (DataScope ds = new DataScope()) {
            List<Object[]> r = Exercise1_A.pontosJogoPorJogador("LOL1234567");
            assertEquals(1200, r.get(0));
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void associarCracha() throws Exception {
        JogadorMapper jm = new JogadorMapper();

        try (DataScope ds = new DataScope()) {
            Exercise1_A.associarCracha(4, "LOL1234567", "begin");
            boolean anyMatch = jm.Read(4).getCrachas().stream().anyMatch(cracha -> cracha.getId().getIdJogo().equals("LOL1234567") && cracha.getId().getNome().equals("begin"));
            assertTrue(anyMatch);
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void iniciarConversa() throws Exception {
        ConversaMapper cm = new ConversaMapper();

        try (DataScope ds = new DataScope()) {
            int idConversa = Exercise1_A.iniciarConversa(4, "Conversa Teste");
            assertEquals("Conversa Teste", cm.Read(idConversa).getNome());
            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void juntarConversa() throws Exception {
        ConversaMapper cm = new ConversaMapper();

        try (DataScope ds = new DataScope()) {
            int idConversa = Exercise1_A.iniciarConversa(4, "Conversa Teste2");
            Exercise1_A.juntarConversa(2, idConversa);
            boolean anyMatch = cm.Read(idConversa).getParticipantes().stream().anyMatch(jogador -> jogador.getId() == 3);
            assertTrue(anyMatch);

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void enviarMensagem() throws Exception {
        ConversaMapper cm = new ConversaMapper();

        try (DataScope ds = new DataScope()) {
            int idConversa = Exercise1_A.iniciarConversa(4, "Conversa Teste3");
            Exercise1_A.juntarConversa(2, idConversa);
            Exercise1_A.enviarMensagem(2, idConversa, "Test Message");
            boolean anyMatch = cm.Read(idConversa).getMensagens().stream().anyMatch(mensagem -> Objects.equals(mensagem.getTexto(), "Test Messagee"));
            assertTrue(anyMatch);

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void getJogadorTotalInfo() throws Exception {
        try (DataScope ds = new DataScope()) {
            List<JogadorTotalInfo> lista = Exercise1_A.getJogadorTotalInfo();
            assertEquals(1, lista.get(0).getIdJogador());

            ds.cancelWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}