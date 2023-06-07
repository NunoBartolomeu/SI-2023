package model.pontuacão;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class PontuacaoId implements Serializable {

    @Column(name = "id_partida", insertable = false, updatable = false)
    private int id_partida;

    @Column(name = "id_jogo", insertable = false, updatable = false)
    private String id_jogo;

    @Column(name = "id_jogador", insertable = false, updatable = false)
    private int id_jogador;

    public PontuacaoId() { }

    public long getIdPartida() { return this.id_partida; }

    public void setIdPartida(int idPartida) { this.id_partida = idPartida; }

    public String getId_jogo() { return this.id_jogo; }

    public void setId_jogo(String id_jogo) { this.id_jogo = id_jogo; }

    public int getId_jogador() { return this.id_jogador; }

    public void setId_jogador(int id_jogador) { this.id_jogador = id_jogador; }
}
