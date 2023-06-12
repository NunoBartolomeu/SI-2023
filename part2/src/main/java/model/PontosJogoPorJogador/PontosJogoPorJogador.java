package model.PontosJogoPorJogador;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class PontosJogoPorJogador {
    @Id
    private int id_jogador;

    private int pontos;

    public PontosJogoPorJogador() { }

    public int getId_jogador() { return id_jogador; }

    public void setId_jogador(int id_jogador) { this.id_jogador = id_jogador; }

    public int getPontos() { return pontos; }

    public void setPontos(int pontos) { this.pontos = pontos; }
}
