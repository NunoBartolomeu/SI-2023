package presentation;

import jakarta.persistence.*;
import model.compra.Compra;
import model.conversa.Conversa;
import model.cracha.Cracha;
import model.estatisticas_jogador.EstatisticasJogador;
import model.jogador.Jogador;
import model.jogo.Jogo;
import model.mensagen.Mensagem;
import model.regiao.Regiao;

import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			Thread.sleep(50);
			em.getTransaction().begin();
			List<Cracha> a = em.createQuery("SELECT j FROM crachas j").getResultList();
			System.out.println("======================================================================================");
			a.forEach((i) -> {
				System.out.println(i.getId());
				/*for (Compra c : i.getCompras()){
					System.out.println(c.getJogador().getUsername());
				}

				 */
			});
			System.out.println("======================================================================================");
			em.getTransaction().commit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
