package presentation;

import jakarta.persistence.*;
import model.partida.Partida;
import model.partida_multijogador.PartidaMultijogador;


import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			Thread.sleep(50);
			em.getTransaction().begin();
			List<Partida> a = em.createQuery("select j from partidas j").getResultList();
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
