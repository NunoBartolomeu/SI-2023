package presentation;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.regiao.Regiao;

import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			em.getTransaction().begin();
			List<Jogador> a = em.createQuery("SELECT j FROM jogadores j").getResultList();
			a.forEach((i) -> {
				i.print();
			});

			em.getTransaction().commit();
		}
		finally {
			em.close();
			emf.close();
		}
	}
}
