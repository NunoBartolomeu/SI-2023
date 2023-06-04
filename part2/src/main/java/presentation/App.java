package presentation;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida.Partida;
import model.partida_multijogador.PartidaMultijogador;
import model.partida_normal.PartidaNormal;


import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			Thread.sleep(50);
			em.getTransaction().begin();
			List<Jogo> a = em.createQuery("select j from jogos j").getResultList();
			System.out.println("======================================================================================");
			a.forEach((i) -> {
				//System.out.println(i.getPartidas());
				for (Partida c : i.getPartidas()){
					System.out.println("\n\n\n\n\n\n\n\n\n\n");
					for (PartidaMultijogador pm: c.getPartidasMultijogador()){
						System.out.println(pm.getId().getId());
					}
					System.out.println("\n\n\n\n\n\n\n\n\n\n");
				}


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
