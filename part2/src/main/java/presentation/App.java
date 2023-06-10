package presentation;

import jakarta.persistence.*;
import model.cracha.Cracha;
import model.jogador.Jogador;
import model.partida.Partida;
import model.partida_multijogador.PartidaMultijogador;
import model.partida_normal.PartidaNormal;
import model.partida_normal.PartidaNormalId;
import model.pontuacão.Pontuacao_Multi_Jogador;


import java.util.List;

public class App {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_SI");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		try {
			Thread.sleep(50);
			em.getTransaction().begin();
			List<Jogador> a = em.createQuery("select j from jogadores j").getResultList();
			System.out.println("======================================================================================");
			a.forEach((i) -> {
				//System.out.println(i.getCrachas());

				for(Cracha c : i.getCrachas()){
					System.out.println(c.getId().getNome());
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
