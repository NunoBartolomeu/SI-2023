package model.pontuac�o;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class PontuacaoId implements Serializable {

    @Column(name = "id_partida", insertable = false, updatable = false)
    private long idPartida;

    @Column(name = "id_jogo", insertable = false, updatable = false)
    private String idJogo;

    @Column(name = "id_jogador", insertable = false, updatable = false)
    private int idJogador;

    public PontuacaoId() { }

    public long getIdPartida() { return this.idPartida; }

    public void setIdPartida(long idPartida) { this.idPartida = idPartida; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }

    public int getIdJogador() { return this.idJogador; }

    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }
}
