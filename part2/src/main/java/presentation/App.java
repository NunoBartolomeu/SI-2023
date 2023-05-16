package presentation;

import jakarta.persistence.*;
import model.tables.user.Jogador;
import model.tables.Regiao;

import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			em.getTransaction().begin();
			List<Regiao> a = em.createQuery("SELECT r FROM regioes r").getResultList();
			a.forEach((i) -> {
				System.out.println(i.getNome());
			});

			List<Jogador> j = em.createQuery("select j from jogadores j").getResultList();
			j.forEach((i) -> {
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
