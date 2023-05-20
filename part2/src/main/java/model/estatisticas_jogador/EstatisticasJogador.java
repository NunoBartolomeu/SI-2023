package model.estatisticas_jogador;

import jakarta.persistence.*;
import model.jogador.Jogador;

@Entity(name = "estatisticas_jogador")
@Table(name = "estatisticas_jogador")
public class EstatisticasJogador {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", unique = true, nullable = false, referencedColumnName = "id")
    private Jogador jogador;

    private int num_jogos;
    private int num_partidas;
    private int total_pontos;

    public EstatisticasJogador() { }

    public Jogador getJogador() { return this.jogador; }

    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public int getNum_jogos() { return this.num_jogos; }

    public void setNum_jogos(int num_jogos) { this.num_jogos = num_jogos; }

    public int getNum_partidas() { return this.num_partidas; }

    public void setNum_partidas(int num_partidas) { this.num_partidas = num_partidas; }

    public int getTotal_pontos() { return this.total_pontos; }

    public void setTotal_pontos(int total_pontos) { this.total_pontos = total_pontos; }

}
