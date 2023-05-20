package model.estatisticas_jogo;

import jakarta.persistence.*;
import model.jogo.Jogo;

@Entity(name = "estatisticas_jogo")
@Table(name = "estatisticas_jogo")
public class EstatisticasJogo {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogo", unique = true, nullable = false, referencedColumnName = "id")
    private Jogo jogo;

    private int num_jogadores;
    private int num_partidas;
    private int total_pontos;
}

