package presentation;

import jakarta.persistence.*;
import model.pontuacão.Pontuacao_Multi_Jogador;


import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			Thread.sleep(50);
			em.getTransaction().begin();
			List<Pontuacao_Multi_Jogador> a = em.createQuery("select j from pontuacoes_multi_jogador j").getResultList();
			System.out.println("======================================================================================");
			a.forEach((i) -> {
				System.out.println(i.getPontos());

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
