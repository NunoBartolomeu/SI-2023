package model.jogador_total_info;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class JogadorTotalInfo {
    @Id
    @Column(name = "id_jogador")
    private int idJogador;

    private int totalJogos;

    private int totalPartidas;

    private int totalPontos;

    public JogadorTotalInfo() { }

    public int getIdJogador() { return idJogador; }

    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }

    public int getTotalJogos() { return totalJogos; }

    public void setTotalJogos(int totalJogos) { this.totalJogos = totalJogos; }

    public int getTotalPartidas() { return totalPartidas; }

    public void setTotalPartidas(int totalPartidas) { this.totalPartidas = totalPartidas; }

    public int getTotalPontos() { return totalPontos; }

    public void setTotalPontos(int totalPontos) { this.totalPontos = totalPontos; }
}

