package presentation;

import jakarta.persistence.*;
import model.conversa.Conversa;


import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			Thread.sleep(50);
			em.getTransaction().begin();
			List<Conversa> a = em.createQuery("select j from conversas j").getResultList();
			System.out.println("======================================================================================");
			a.forEach((i) -> {
				System.out.println(i.getId());
/*
				for(Cracha c : i.getCrachas()){
					System.out.println(c.getId().getNome());
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
