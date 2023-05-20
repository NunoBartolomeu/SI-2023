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

    public EstatisticasJogo() {
    }

    public Jogo getJogo() { return this.jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public int getNum_jogadores() { return this.num_jogadores; }

    public void setNum_jogadores(int num_jogadores) { this.num_jogadores = num_jogadores; }

    public int getNum_partidas() { return this.num_partidas; }

    public void setNum_partidas(int num_partidas) { this.num_partidas = num_partidas; }

    public int getTotal_pontos() { return this.total_pontos; }

    public void setTotal_pontos(int total_pontos) { this.total_pontos = total_pontos; }


}

